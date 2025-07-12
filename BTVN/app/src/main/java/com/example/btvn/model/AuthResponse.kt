package com.example.btvn.model

/**
 * `AuthResponse` là một **sealed class** dùng để biểu diễn các **trạng thái khác nhau**
 * trong suốt quá trình xác thực người dùng (authentication), như đăng nhập, đăng ký, v.v.
 * Sealed class giúp chúng ta đảm bảo rằng tất cả các trạng thái có thể có đều được liệt kê rõ ràng,
 * và dễ dàng xử lý bằng câu lệnh `when` mà không cần `else`.
 */
sealed class AuthResponse {

    /**
     * Trạng thái **`Loading`**: Biểu thị rằng một thao tác xác thực đang diễn ra (ví dụ: đang gửi yêu cầu đăng nhập).
     */
    object Loading : AuthResponse()

    /**
     * Trạng thái **`Success`**: Biểu thị rằng thao tác xác thực đã thành công.
     *
     * @param data Dữ liệu kết quả của thao tác thành công. Có thể là một đối tượng `User`
     * hoặc một thông báo thành công (ví dụ: "Mã OTP đã được gửi"). Mặc định là `null`.
     */
    data class Success(val data: Any? = null) : AuthResponse()

    /**
     * Trạng thái **`Error`**: Biểu thị rằng thao tác xác thực đã thất bại.
     *
     * @param errorMessage Một chuỗi mô tả rõ ràng về lỗi đã xảy ra, hiển thị cho người dùng.
     */
    data class Error(val errorMessage: String) : AuthResponse()

    /**
     * Trạng thái **`Idle`**: Biểu thị trạng thái ban đầu hoặc trạng thái không có hành động nào đang diễn ra.
     * Thường dùng để đặt lại trạng thái trước khi một thao tác mới bắt đầu.
     */
    object Idle : AuthResponse()
}