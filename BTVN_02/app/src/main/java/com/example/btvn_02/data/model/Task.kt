package com.example.btvn_02.data.model

import androidx.compose.ui.graphics.Color
import java.util.UUID

// Định nghĩa lớp Task đại diện cho một công việc
data class Task(
    val id: String = UUID.randomUUID().toString(), // ID duy nhất cho mỗi công việc
    val title: String, // Tiêu đề công việc
    val description: String, // Mô tả công việc
    val status: TaskStatus = TaskStatus.TODO // Trạng thái của công việc, mặc định là TODO
)

// Enum để định nghĩa các trạng thái của công việc
enum class TaskStatus {
    TODO,       // Sẽ làm
    DOING,      // Đang làm
    DONE        // Đã hoàn thành
}

// Hàm mở rộng để lấy màu sắc tương ứng với trạng thái công việc
fun TaskStatus.getColor(): Color {
    return when (this) {
        TaskStatus.TODO -> Color(0xFFADD8E6) // Light Blue
        TaskStatus.DOING -> Color(0xFFF08080) // Light Coral
        TaskStatus.DONE -> Color(0xFF90EE90) // Light Green
    }
}