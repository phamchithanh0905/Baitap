package com.example.btvn_01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btvn_01.data.model.Task
import com.example.btvn_01.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * [ViewModel] cho ứng dụng quản lý công việc.
 * Chịu trách nhiệm lấy và quản lý dữ liệu công việc từ [TaskRepository]
 * và cung cấp cho các thành phần UI.
 */
class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    // Trạng thái cho TaskListScreen
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Trạng thái cho TaskDetailScreen
    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    private val _detailIsLoading = MutableStateFlow(false)
    val detailIsLoading: StateFlow<Boolean> = _detailIsLoading

    private val _detailErrorMessage = MutableStateFlow<String?>(null)
    val detailErrorMessage: StateFlow<String?> = _detailErrorMessage

    // Trạng thái cho phản hồi hoạt động xóa
    private val _deleteMessage = MutableStateFlow<String?>(null)
    val deleteMessage: StateFlow<String?> = _deleteMessage


    init {
        fetchTasks() // Tự động tải danh sách công việc khi ViewModel được khởi tạo
    }

    /**
     * Lấy danh sách các công việc từ repository.
     * Cập nhật trạng thái loading và lỗi.
     */
    fun fetchTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            repository.getTasks()
                .onSuccess { successData: List<Task> ->
                    _tasks.value = successData
                }
                .onFailure { exception: Throwable ->
                    _errorMessage.value = exception.message ?: "Unknown error"
                }
            _isLoading.value = false
        }
    }

    /**
     * Lấy chi tiết một công việc cụ thể bằng ID.
     * Cập nhật trạng thái loading, lỗi và công việc đã chọn.
     * @param taskId ID của công việc cần lấy chi tiết.
     */
    fun fetchTaskDetails(taskId: Int) {
        viewModelScope.launch {
            _detailIsLoading.value = true
            _detailErrorMessage.value = null
            _selectedTask.value = null

            repository.getTaskById(taskId)
                .onSuccess { task: Task ->
                    _selectedTask.value = task
                }
                .onFailure { exception: Throwable ->
                    _detailErrorMessage.value = exception.message ?: "Failed to load task details"
                }
            _detailIsLoading.value = false
        }
    }

    /**
     * Xóa một công việc bằng ID.
     * Cập nhật thông báo xóa và sau đó tải lại danh sách công việc.
     * @param taskId ID của công việc cần xóa.
     */
    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            _deleteMessage.value = null // Xóa thông báo cũ trước khi xóa mới
            repository.deleteTask(taskId)
                .onSuccess {
                    _deleteMessage.value = "Task deleted successfully!"
                    fetchTasks() // Tải lại danh sách để cập nhật UI
                }
                .onFailure { exception ->
                    _deleteMessage.value = "Error deleting task: ${exception.message ?: "Unknown error"}"
                }
        }
    }

    /**
     * Xóa thông báo xóa sau khi UI đã tiêu thụ nó.
     * Ngăn chặn việc [LaunchedEffect] kích hoạt lại không cần thiết.
     */
    fun clearDeleteMessage() {
        _deleteMessage.value = null
    }
}