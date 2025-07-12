package com.example.btvn_01.repository

import com.example.btvn_01.data.model.Task
import com.example.btvn_01.data.model.TaskResponse
import com.example.btvn_01.data.model.SingleTaskResponse
import com.example.btvn_01.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Lớp Repository để xử lý logic lấy dữ liệu từ API.
 * Nó giao tiếp với [ApiService] thông qua [RetrofitInstance] và xử lý kết quả.
 */
class TaskRepository {

    /**
     * Lấy danh sách tất cả các công việc.
     * Hoạt động trên Dispatchers.IO để thực hiện các thao tác mạng.
     * @return [Result]<[List]<[Task]>> chứa danh sách các công việc nếu thành công, hoặc một [Exception] nếu thất bại.
     */
    suspend fun getTasks(): Result<List<Task>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getTasks()
                if (response.isSuccessful) {
                    response.body()?.let { taskResponse ->
                        if (taskResponse.isSuccess) {
                            kotlin.Result.success(taskResponse.data)
                        } else {
                            kotlin.Result.failure(Exception("API reported failure: ${taskResponse.message}"))
                        }
                    } ?: kotlin.Result.failure(Exception("Response body is null"))
                } else {
                    kotlin.Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                kotlin.Result.failure(e)
            }
        }
    }

    /**
     * Lấy thông tin chi tiết của một công việc dựa trên ID.
     * Hoạt động trên Dispatchers.IO để thực hiện các thao tác mạng.
     * @param taskId ID của công việc cần lấy.
     * @return [Result]<[Task]> chứa thông tin chi tiết của công việc nếu thành công, hoặc một [Exception] nếu thất bại.
     */
    suspend fun getTaskById(taskId: Int): Result<Task> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getTaskById(taskId)
                if (response.isSuccessful) {
                    response.body()?.let { singleTaskResponse ->
                        if (singleTaskResponse.isSuccess) {
                            kotlin.Result.success(singleTaskResponse.data)
                        } else {
                            kotlin.Result.failure(Exception("API reported failure: ${singleTaskResponse.message}"))
                        }
                    } ?: kotlin.Result.failure(Exception("Response body is null"))
                } else {
                    kotlin.Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                kotlin.Result.failure(e)
            }
        }
    }

    /**
     * Xóa một công việc dựa trên ID.
     * Hoạt động trên Dispatchers.IO để thực hiện các thao tác mạng.
     * @param taskId ID của công việc cần xóa.
     * @return [Result]<[Unit]> cho biết xóa thành công ([Unit]) hoặc một [Exception] nếu thất bại.
     */
    suspend fun deleteTask(taskId: Int): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.deleteTaskById(taskId)
                if (response.isSuccessful) {
                    kotlin.Result.success(Unit) // Unit chỉ ra rằng xóa thành công và không có nội dung trả về.
                } else {
                    kotlin.Result.failure(Exception("Failed to delete task: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                kotlin.Result.failure(e)
            }
        }
    }
}