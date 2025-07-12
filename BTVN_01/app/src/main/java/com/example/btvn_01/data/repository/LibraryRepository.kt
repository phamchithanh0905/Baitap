package com.example.btvn_01.data.repository

import com.example.btvn_01.data.model.Book
import com.example.btvn_01.data.model.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

/**
 * Repository chịu trách nhiệm quản lý dữ liệu liên quan đến sinh viên và sách trong hệ thống thư viện.
 * Hỗ trợ mô hình mỗi sinh viên có danh sách sách riêng (không chia sẻ).
 */
class LibraryRepository(
    private val applicationScope: CoroutineScope
) {
    // Danh sách sinh viên ban đầu, mỗi sinh viên có danh sách sách cá nhân riêng biệt
    private val _students = MutableStateFlow(
        listOf(
            Student(
                id = "student_001",
                name = "Nguyen Van A",
                personalBooks = mutableListOf(
                    Book("book_001_A", "Sách 01", isBorrowed = false),
                    Book("book_002_A", "Sách 02", isBorrowed = true)
                )
            ),
            Student(
                id = "student_002",
                name = "Nguyen Thi B",
                personalBooks = mutableListOf(
                    Book("book_001_B", "Sách 01", isBorrowed = false)
                )
            ),
            Student(
                id = "student_003",
                name = "Nguyen Van C",
                personalBooks = mutableListOf()
            )
        )
    )
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    // Danh sách các mẫu sách có sẵn để thêm vào cho từng sinh viên
    private val _availableBookTemplates = MutableStateFlow(
        listOf(
            Book("template_001", "Sách 01"),
            Book("template_002", "Sách 02")
        )
    )
    val availableBookTemplates: StateFlow<List<Book>> = _availableBookTemplates.asStateFlow()

    // ID của sinh viên hiện tại đang được chọn
    private val _currentStudentId = MutableStateFlow<String?>(null)

    // Sinh viên hiện tại (được tính toán từ ID)
    val currentStudent: StateFlow<Student?> = combine(_currentStudentId, students) { id, studentsList ->
        if (id == null) studentsList.firstOrNull()
        else studentsList.find { it.id == id }
    }.stateIn(
        scope = applicationScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _students.value.firstOrNull()
    )

    init {
        _currentStudentId.value = _students.value.firstOrNull()?.id
    }

    /** Đặt sinh viên hiện tại dựa theo ID */
    fun setCurrentStudent(studentId: String?) {
        _currentStudentId.value = studentId
    }

    /** Lấy sinh viên theo ID */
    fun getStudentById(id: String): Student? {
        return _students.value.find { it.id == id }
    }

    /** Thêm một mẫu sách mới để sinh viên có thể chọn thêm */
    fun addAvailableBookTemplate(book: Book) {
        _availableBookTemplates.update { it + book }
    }

    /** Xoá một mẫu sách khỏi danh sách mẫu */
    fun deleteAvailableBookTemplate(bookId: String) {
        _availableBookTemplates.update { templates ->
            templates.filter { it.id != bookId }
        }
    }

    /** Thêm một cuốn sách vào danh sách cá nhân của sinh viên */
    fun addBookForStudent(studentId: String, book: Book) {
        _students.update { students ->
            students.map { student ->
                if (student.id == studentId) {
                    student.copy(
                        personalBooks = (student.personalBooks + book).toMutableList()
                    )
                } else student
            }
        }
    }

    /** Xoá một cuốn sách khỏi sinh viên cụ thể */
    fun deleteBookForStudent(studentId: String, bookId: String) {
        _students.update { students ->
            students.map { student ->
                if (student.id == studentId) {
                    student.copy(
                        personalBooks = student.personalBooks
                            .filter { it.id != bookId }
                            .toMutableList()
                    )
                } else student
            }
        }
    }

    /** Thêm một sinh viên mới vào hệ thống */
    fun addStudent(student: Student) {
        _students.update { it + student }
    }

    /** Xoá sinh viên khỏi hệ thống */
    fun deleteStudent(studentId: String) {
        _students.update { students ->
            val updated = students.filter { it.id != studentId }
            if (_currentStudentId.value == studentId) {
                _currentStudentId.value = updated.firstOrNull()?.id
            }
            updated
        }
    }

    /**
     * Cập nhật trạng thái mượn/trả sách cho một sinh viên.
     * Dùng để toggle trạng thái khi người dùng chọn checkbox.
     */
    fun toggleBookBorrowedStatusForStudent(studentId: String, bookId: String, isBorrowed: Boolean) {
        _students.update { students ->
            students.map { student ->
                if (student.id == studentId) {
                    student.copy(
                        personalBooks = student.personalBooks.map { book ->
                            if (book.id == bookId) book.copy(isBorrowed = isBorrowed)
                            else book
                        }.toMutableList()
                    )
                } else student
            }
        }
    }
}
