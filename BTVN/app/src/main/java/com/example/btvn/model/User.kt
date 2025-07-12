package com.example.btvn.model

/**
 * `User` là một **data class** dùng để biểu diễn các thông tin cơ bản về một người dùng
 * trong ứng dụng. Nó được thiết kế để lưu trữ và truy xuất dữ liệu người dùng từ các nguồn như Firebase.
 */
data class User(
    /**
     * **Mã định danh duy nhất (Unique ID)** của người dùng.
     * Thường là UID được cung cấp bởi Firebase Authentication (ví dụ: `firebaseAuth.currentUser?.uid`).
     * Giá trị mặc định là chuỗi rỗng.
     */
    val uid: String = "",

    /**
     * **Tên đầy đủ** của người dùng.
     * Giá trị mặc định là chuỗi rỗng.
     */
    val name: String = "",

    /**
     * **Địa chỉ email** của người dùng.
     * Đây thường là email đã được xác thực thông qua Firebase Authentication.
     * Giá trị mặc định là chuỗi rỗng.
     */
    val email: String = "",

    /**
     * **Ngày sinh** của người dùng, được lưu trữ dưới dạng chuỗi.
     * Định dạng đề xuất là "DD/MM/YYYY" để dễ đọc và xử lý.
     * Giá trị mặc định là chuỗi rỗng.
     */
    val dateOfBirth: String = ""
    // Bạn có thể mở rộng data class này bằng cách thêm các trường khác
    // tùy theo yêu cầu của ứng dụng, ví dụ:
    // val avatarUrl: String = "",       // URL ảnh đại diện
    // val phoneNumber: String = "",    // Số điện thoại
    // val address: String = ""         // Địa chỉ
)