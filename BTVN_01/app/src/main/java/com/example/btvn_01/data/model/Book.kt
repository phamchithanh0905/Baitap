package com.example.btvn_01.data.model

/**
 * Lớp dữ liệu đại diện cho một cuốn sách trong hệ thống.
 *
 * @param id Mã định danh duy nhất cho sách (ví dụ: "book_001").
 * @param title Tên hoặc tiêu đề của cuốn sách.
 * @param isBorrowed Trạng thái mượn sách. Nếu sách dùng chung giữa các sinh viên, trạng thái này sẽ phản ánh
 *                   việc sách có đang được mượn hay không. Tuy nhiên, nếu mỗi sinh viên có danh sách sách riêng,
 *                   trường này có thể bỏ qua hoặc sử dụng để hiển thị trạng thái đã đọc, đánh dấu...
 */
data class Book(
    val id: String,
    val title: String,
    val isBorrowed: Boolean = false
)
