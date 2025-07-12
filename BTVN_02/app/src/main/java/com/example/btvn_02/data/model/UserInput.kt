package com.example.btvn_02.data.model

/**
 * Lớp dữ liệu đại diện cho toàn bộ thông tin người dùng cung cấp
 * trong quá trình đặt lại mật khẩu (Reset Password).
 *
 * Các thuộc tính bao gồm:
 * - [email]: Email của người dùng, được nhập ở bước đầu tiên.
 * - [verificationCode]: Mã xác thực gửi về email để xác minh danh tính.
 * - [newPassword]: Mật khẩu mới do người dùng đặt lại.
 * - [confirmPassword]: Xác nhận lại mật khẩu mới để kiểm tra trùng khớp.
 */
data class UserInput(
    var email: String = "",
    var verificationCode: String = "",
    var newPassword: String = "",
    var confirmPassword: String = ""
)
