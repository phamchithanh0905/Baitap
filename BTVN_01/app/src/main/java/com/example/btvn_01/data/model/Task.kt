package com.example.btvn_01.data.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Data class đại diện cho một công việc (Task).
 */
data class Task(
    /** ID của công việc. */
    val id: Int,
    /** Tiêu đề của công việc. */
    val title: String,
    /** URL ảnh mô tả công việc (có thể null). */
    @SerializedName("desImageURL")
    val desImageUrl: String?,
    /** Mô tả chi tiết công việc. */
    val description: String,
    /** Trạng thái hiện tại của công việc. */
    val status: String,
    /** Mức độ ưu tiên của công việc. */
    val priority: String,
    /** Danh mục của công việc. */
    val category: String,
    /** Ngày đáo hạn của công việc từ API (chuỗi định dạng ISO 8601). */
    @SerializedName("dueDate")
    val apiDueDate: String,
    /** Thời gian tạo công việc (có thể null). */
    @SerializedName("createdAt")
    val createdAt: String?,
    /** Thời gian cập nhật công việc lần cuối (có thể null). */
    @SerializedName("updatedAt")
    val updatedAt: String?,
    /** Danh sách các công việc con (có thể null). */
    val subtasks: List<Subtask>?,
    /** Danh sách các tệp đính kèm (có thể null). */
    val attachments: List<Attachment>?,
    /** Danh sách các nhắc nhở (có thể null). */
    val reminders: List<Reminder>?
) {
    /**
     * Trả về thời gian đáo hạn của công việc dưới định dạng "HH:mm".
     */
    val time: String
        get() = try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            parser.timeZone = TimeZone.getTimeZone("UTC")
            val formatter = SimpleDateFormat("HH:mm", Locale.US)
            formatter.format(parser.parse(apiDueDate) ?: Date())
        } catch (e: Exception) {
            "N/A"
        }

    /**
     * Trả về ngày đáo hạn của công việc dưới định dạng "yyyy-MM-dd".
     */
    val date: String
        get() = try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            parser.timeZone = TimeZone.getTimeZone("UTC")
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            formatter.format(parser.parse(apiDueDate) ?: Date())
        } catch (e: Exception) {
            "N/A"
        }
}

/**
 * Data class đại diện cho một công việc con (Subtask).
 */
data class Subtask(
    /** ID của công việc con. */
    val id: Int,
    /** Tiêu đề của công việc con. */
    val title: String,
    /** Trạng thái hoàn thành của công việc con. */
    val isCompleted: Boolean
)

/**
 * Data class đại diện cho một tệp đính kèm (Attachment).
 */
data class Attachment(
    /** ID của tệp đính kèm. */
    val id: Int,
    /** Tên tệp. */
    val fileName: String,
    /** URL của tệp. */
    val fileUrl: String
)

/**
 * Data class đại diện cho một nhắc nhở (Reminder).
 */
data class Reminder(
    /** ID của nhắc nhở. */
    val id: Int,
    /** Thời gian của nhắc nhở. */
    val time: String,
    /** Loại nhắc nhở. */
    val type: String
)