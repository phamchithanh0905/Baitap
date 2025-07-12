package com.example.btvn_01.data.remote

import com.example.btvn_01.data.model.TaskResponse
import com.example.btvn_01.data.model.SingleTaskResponse
import retrofit2.Response
import retrofit2.http.DELETE // Import for DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/researchUTH/tasks")
    suspend fun getTasks(): Response<TaskResponse>

    @GET("api/researchUTH/task/{id}")
    suspend fun getTaskById(@Path("id") taskId: Int): Response<SingleTaskResponse>

    @DELETE("api/researchUTH/task/{id}") // DELETE endpoint for a single task
    suspend fun deleteTaskById(@Path("id") taskId: Int): Response<Unit> // Response<Unit> for empty successful body
}