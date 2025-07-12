package com.example.btvn_02.ui.tasklist

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.btvn_02.data.model.Task
import com.example.btvn_02.data.model.TaskStatus

// ViewModel cho màn hình Task List
class TaskListViewModel : ViewModel() {

    // Danh sách các công việc, sử dụng mutableStateListOf để Compose có thể theo dõi sự thay đổi
    private val _tasks = mutableStateListOf<Task>().apply {
        // Thêm dữ liệu mẫu để hiển thị
        add(Task(title = "Complete Android Project", description = "Finish the UI, integrate API, and write documentation", status = TaskStatus.TODO))
        add(Task(title = "Complete Android Project", description = "Finish the UI, integrate API, and write documentation", status = TaskStatus.DOING))
        add(Task(title = "Complete Android Project", description = "Finish the UI, integrate API, and write documentation", status = TaskStatus.DONE))
        add(Task(title = "Complete Android Project", description = "Finish the UI, integrate API, and write documentation", status = TaskStatus.DOING))
    }
    val tasks: List<Task> = _tasks // Public list, chỉ cho phép đọc

    // Thêm một công việc mới vào danh sách
    fun addTask(task: Task) {
        _tasks.add(task)
    }

    // Xóa một công việc khỏi danh sách (nếu cần)
    fun removeTask(task: Task) {
        _tasks.remove(task)
    }
}