package com.example.btvn.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btvn.data.repository.IUserRepository
import com.example.btvn.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {

    // Lưu thông tin người dùng (state nội bộ)
    private val _userProfile = MutableStateFlow<User?>(null)
    val userProfile: StateFlow<User?> = _userProfile.asStateFlow()

    // Trạng thái đang tải
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Kết quả cập nhật thành công hay không
    private val _updateSuccess = MutableStateFlow<Boolean?>(null)
    val updateSuccess: StateFlow<Boolean?> = _updateSuccess.asStateFlow()

    // Thông báo lỗi
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    /**
     * Tải hồ sơ người dùng từ repository bằng userId.
     */
    fun loadUserProfile(userId: String) {
        Log.d("UserViewModel", "Loading user profile for UID: $userId")
        viewModelScope.launch {
            userRepository.getUserProfile(userId)
                .onStart {
                    _isLoading.value = true
                    _error.value = null
                }
                .catch { e ->
                    _error.value = "Không thể tải hồ sơ: ${e.localizedMessage}"
                    _isLoading.value = false
                    Log.e("UserViewModel", "Load failed: ${e.localizedMessage}", e)
                }
                .collect { user ->
                    _userProfile.value = user
                    _isLoading.value = false
                    Log.d("UserViewModel", "Tải thành công: ${user?.email ?: "null"}")
                }
        }
    }

    /**
     * Cập nhật hồ sơ người dùng và lưu lại vào repository.
     */
    fun updateUserProfile(user: User) {
        Log.d("UserViewModel", "Đang cập nhật UID: ${user.uid}")
        _isLoading.value = true
        _updateSuccess.value = null
        _error.value = null

        viewModelScope.launch {
            try {
                val success = userRepository.saveUserProfile(user)
                _updateSuccess.value = success
                _isLoading.value = false

                if (success) {
                    _userProfile.value = user
                    Log.d("UserViewModel", "Cập nhật thành công: ${user.email}")
                } else {
                    _error.value = "Không thể lưu hồ sơ người dùng."
                    Log.e("UserViewModel", "Lưu thất bại: ${user.email}")
                }
            } catch (e: Exception) {
                _updateSuccess.value = false
                _isLoading.value = false
                _error.value = "Lỗi khi cập nhật: ${e.localizedMessage}"
                Log.e("UserViewModel", "Update failed: ${e.localizedMessage}", e)
            }
        }
    }

    /**
     * Xóa lỗi sau khi đã hiển thị cho người dùng.
     */
    fun resetErrorState() {
        _error.value = null
    }

    /**
     * Xóa trạng thái thành công sau khi xử lý xong.
     */
    fun resetUpdateSuccessState() {
        _updateSuccess.value = null
    }
}
