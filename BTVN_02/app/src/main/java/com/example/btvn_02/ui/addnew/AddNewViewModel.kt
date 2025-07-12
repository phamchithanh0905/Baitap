package com.example.btvn_02.ui.addnew

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.btvn_02.data.model.Task

// ViewModel để quản lý trạng thái và logic của màn hình thêm công việc
class AddNewViewModel : ViewModel() {

    // Biến trạng thái cho tiêu đề công việc, tự động recompose khi thay đổi
    var taskTitle by mutableStateOf("")
        private set // Chỉ nội bộ ViewModel mới có thể thay đổi

    // Biến trạng thái cho mô tả công việc
    var taskDescription by mutableStateOf("")
        private set

    // Cập nhật tiêu đề công việc
    fun updateTaskTitle(newTitle: String) {
        taskTitle = newTitle
    }

    // Cập nhật mô tả công việc
    fun updateTaskDescription(newDescription: String) {
        taskDescription = newDescription
    }

    // Tạo một đối tượng Task mới từ dữ liệu nhập vào
    fun createTask(): Task {
        return Task(title = taskTitle, description = taskDescription)
    }

    // Đặt lại các trường nhập liệu về trạng thái ban đầu (rỗng)
    fun resetFields() {
        taskTitle = ""
        taskDescription = ""
    }
}