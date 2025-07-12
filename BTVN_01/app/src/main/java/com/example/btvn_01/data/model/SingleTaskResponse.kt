package com.example.btvn_01.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class đại diện cho phản hồi khi lấy chi tiết một task từ API.
 */
data class SingleTaskResponse(
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
     * Dữ liệu chi tiết của task.
     */
    @SerializedName("data")
    val data: Task
)