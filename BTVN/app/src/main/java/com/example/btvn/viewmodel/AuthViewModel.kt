// immersive id="auth_viewmodel_updated_final" type="code" title="AuthViewModel.kt Final Updated"
package com.example.btvn.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btvn.data.repository.FirebaseAuthRepository // Import FirebaseAuthRepository
import com.example.btvn.data.repository.IAuthRepository
import com.example.btvn.model.AuthResponse
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: IAuthRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository // Inject FirebaseAuthRepository để lắng nghe Phone Auth Flow
) : ViewModel() {

    private val _authStatus = MutableStateFlow<AuthResponse>(AuthResponse.Idle)
    val authStatus: StateFlow<AuthResponse> = _authStatus.asStateFlow()

    private val _currentUserData = MutableStateFlow<FirebaseUser?>(null)
    val currentUserData: StateFlow<FirebaseUser?> = _currentUserData.asStateFlow()

    init {
        // Lắng nghe thay đổi trạng thái xác thực từ Firebase Auth State Changes (đăng nhập/đăng xuất chung)
        viewModelScope.launch {
            authRepository.getAuthStateChanges().collect { user ->
                _currentUserData.value = user
                if (user != null) {
                    // Cập nhật trạng thái thành công nếu người dùng đã đăng nhập
                    // Chỉ cập nhật nếu trạng thái hiện tại không phải là Loading (tránh ghi đè trạng thái đang xử lý)
                    if (_authStatus.value !is AuthResponse.Loading) {
                        _authStatus.value = AuthResponse.Success(user.uid)
                    }
                } else {
                    _authStatus.value = AuthResponse.Idle // Đặt lại trạng thái nếu người dùng đăng xuất
                }
            }
        }

        // Lắng nghe các trạng thái từ Phone Auth Verification thông qua Channel trong Repository
        viewModelScope.launch {
            firebaseAuthRepository.authResponseFlow.collect { response ->
                _authStatus.value = response // Cập nhật trạng thái ViewModel từ Channel của Phone Auth
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        _authStatus.value = AuthResponse.Loading
        viewModelScope.launch {
            val response = authRepository.signInWithGoogle(idToken)
            _authStatus.value = response
        }
    }

    fun signInWithEmailPassword(email: String, password: String) {
        _authStatus.value = AuthResponse.Loading
        viewModelScope.launch {
            val response = authRepository.signInWithEmailPassword(email, password)
            _authStatus.value = response
        }
    }

    fun registerWithEmailPassword(email: String, password: String) {
        _authStatus.value = AuthResponse.Loading
        viewModelScope.launch {
            val response = authRepository.registerWithEmailPassword(email, password)
            _authStatus.value = response
        }
    }

    fun startPhoneAuth(activity: Activity, phoneNumber: String) {
        _authStatus.value = AuthResponse.Loading // Đặt trạng thái loading ngay lập tức
        viewModelScope.launch {
            // Gọi hàm trong repository. Hàm này không trả về AuthResponse mà gửi qua Channel.
            // Kết quả sẽ được lắng nghe trong block init của ViewModel từ authResponseFlow.
            authRepository.startPhoneVerification(activity, phoneNumber)
        }
    }

    fun verifyPhoneNumberWithCode(code: String) {
        _authStatus.value = AuthResponse.Loading
        viewModelScope.launch {
            val response = authRepository.verifyPhoneNumberWithCode(code)
            _authStatus.value = response
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
            _authStatus.value = AuthResponse.Idle // Reset trạng thái khi đăng xuất
        }
    }

    fun resetAuthStatus() {
        _authStatus.value = AuthResponse.Idle
    }
}
