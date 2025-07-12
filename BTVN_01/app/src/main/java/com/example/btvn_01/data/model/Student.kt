package com.example.btvn_01.data.model

/**
 * Lớp dữ liệu đại diện cho một sinh viên trong hệ thống thư viện.
 *
 * @param id Mã định danh duy nhất của sinh viên (ví dụ: "student_001").
 * @param name Tên đầy đủ của sinh viên.
 * @param personalBooks Danh sách các cuốn sách riêng mà sinh viên này sở hữu hoặc quản lý.
 *        Mỗi sinh viên sẽ có danh sách sách cá nhân riêng biệt — không chia sẻ với sinh viên khác.
 */
data class Student(
    val id: String,
    val name: String,
    val personalBooks: MutableList<Book> = mutableListOf()
)
