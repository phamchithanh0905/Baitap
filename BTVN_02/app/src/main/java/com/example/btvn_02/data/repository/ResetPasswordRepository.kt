package com.example.btvn_02.data.repository

import com.example.btvn_02.data.model.UserInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository quản lý trạng thái đầu vào của người dùng trong toàn bộ
 * quy trình quên mật khẩu: Nhập email → Nhập mã xác thực → Đặt lại mật khẩu.
 *
 * Dữ liệu được lưu trữ thông qua StateFlow để đảm bảo khả năng quan sát
 * và phản ứng theo thời gian thực trong UI.
 */
class ResetPasswordRepository {

    // StateFlow lưu trữ dữ liệu người dùng (mutable, chỉ dùng nội bộ)
    private val _userInput = MutableStateFlow(UserInput())

    // StateFlow chỉ đọc, dùng để expose ra bên ngoài (ViewModel/Composables)
    val userInput: StateFlow<UserInput> = _userInput.asStateFlow()

    /** Cập nhật email người dùng */
    fun updateEmail(email: String) {
        _userInput.value = _userInput.value.copy(email = email)
    }

    /** Cập nhật mã xác thực do người dùng nhập */
    fun updateVerificationCode(code: String) {
        _userInput.value = _userInput.value.copy(verificationCode = code)
    }

    /** Cập nhật mật khẩu mới */
    fun updateNewPassword(password: String) {
        _userInput.value = _userInput.value.copy(newPassword = password)
    }

    /** Cập nhật phần xác nhận lại mật khẩu mới */
    fun updateConfirmPassword(password: String) {
        _userInput.value = _userInput.value.copy(confirmPassword = password)
    }

    /** Đặt lại toàn bộ dữ liệu người dùng về giá trị mặc định */
    fun resetInput() {
        _userInput.value = UserInput()
    }

    /*
     * Trong ứng dụng thực tế, có thể mở rộng thêm các hàm như:
     * - validateEmailFormat(): Boolean
     * - callVerifyCodeAPI(email, code): Result
     * - callResetPasswordAPI(email, newPassword): Result
     */
}
