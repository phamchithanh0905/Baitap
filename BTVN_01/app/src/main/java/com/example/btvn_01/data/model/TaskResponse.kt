package com.example.btvn_01.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class đại diện cho phản hồi khi lấy danh sách các công việc (tasks) từ API.
 */
data class TaskResponse(
    /**
     * Trạng thái thành công của yêu cầu API.
     */
    @SerializedName("isSuccess")
    val isSuccess: Boolean,

    /**
     * Thông báo từ server.
     */
    @SerializedName("message")
    val message: String,

    /**
     * Danh sách các đối tượng Task.
     */
    @SerializedName("data")
    val data: List<Task>
)