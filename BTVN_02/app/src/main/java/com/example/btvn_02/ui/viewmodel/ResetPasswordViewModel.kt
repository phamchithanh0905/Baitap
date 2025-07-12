package com.example.btvn_02.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.btvn_02.data.repository.ResetPasswordRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel chính quản lý toàn bộ luồng khôi phục mật khẩu:
 * - Nhập email
 * - Xác minh mã
 * - Đặt lại mật khẩu
 * - Xác nhận thông tin
 */
class ResetPasswordViewModel(private val repository: ResetPasswordRepository) : ViewModel() {

    // --- UI State: Màn hình "Quên mật khẩu" ---
    val forgotPasswordUiState: StateFlow<ForgotPasswordUiState> = repository.userInput
        .map { input -> ForgotPasswordUiState(email = input.email) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ForgotPasswordUiState()
        )

    fun updateEmail(email: String) = repository.updateEmail(email)

    // --- UI State: Màn hình "Xác minh mã" ---
    val verifyCodeUiState: StateFlow<VerifyCodeUiState> = repository.userInput
        .map { input ->
            VerifyCodeUiState(
                email = input.email,
                verificationCode = input.verificationCode
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = VerifyCodeUiState()
        )

    fun updateVerificationCode(code: String) = repository.updateVerificationCode(code)

    // --- UI State: Màn hình "Tạo mật khẩu mới" ---
    val resetPasswordUiState: StateFlow<ResetPasswordUiState> = repository.userInput
        .map { input ->
            ResetPasswordUiState(
                newPassword = input.newPassword,
                confirmPassword = input.confirmPassword
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ResetPasswordUiState()
        )

    fun updateNewPassword(password: String) = repository.updateNewPassword(password)
    fun updateConfirmPassword(password: String) = repository.updateConfirmPassword(password)

    // --- UI State: Màn hình "Xác nhận toàn bộ thông tin" ---
    val confirmUiState: StateFlow<ConfirmUiState> = repository.userInput
        .map { input ->
            ConfirmUiState(
                email = input.email,
                verificationCode = input.verificationCode,
                newPassword = input.newPassword,
                confirmPassword = input.confirmPassword
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ConfirmUiState()
        )

    /** Đặt lại toàn bộ dữ liệu người dùng về trạng thái ban đầu */
    fun resetAllInput() = repository.resetInput()

    // --- Factory hỗ trợ khởi tạo ViewModel với Repository được inject từ ngoài ---
    companion object {
        fun Factory(repository: ResetPasswordRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ResetPasswordViewModel::class.java)) {
                        return ResetPasswordViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }
}

// --- Dữ liệu trạng thái UI cho từng màn hình ---

/** Trạng thái màn hình nhập email */
data class ForgotPasswordUiState(
    val email: String = ""
)

/** Trạng thái màn hình nhập mã xác thực */
data class VerifyCodeUiState(
    val email: String = "",
    val verificationCode: String = ""
)

/** Trạng thái màn hình đặt lại mật khẩu */
data class ResetPasswordUiState(
    val newPassword: String = "",
    val confirmPassword: String = ""
)

/** Trạng thái màn hình xác nhận cuối cùng */
data class ConfirmUiState(
    val email: String = "",
    val verificationCode: String = "",
    val newPassword: String = "",
    val confirmPassword: String = ""
)
