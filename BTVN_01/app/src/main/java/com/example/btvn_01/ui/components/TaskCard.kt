package com.example.btvn_01.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Square
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.btvn_01.data.model.Task

/**
 * Composable dùng để hiển thị một thẻ công việc (Task).
 *
 * @param task Đối tượng Task chứa dữ liệu để hiển thị.
 * @param onClick Lambda sẽ được gọi khi thẻ công việc được nhấp, truyền vào ID của công việc.
 */
@Composable
fun TaskCard(task: Task, onClick: (Int) -> Unit) {
    // Xác định màu nền của thẻ dựa trên trạng thái công việc.
    val cardColor = when (task.status) {
        "In Progress" -> Color(0xFFF06292) // Màu hồng cho trạng thái "In Progress".
        "Pending" -> {
            // Xác định màu cho trạng thái "Pending" dựa trên tiêu đề cụ thể.
            when (task.title) {
                "Doctor Appointment 2" -> Color(0xFFC8E6C9) // Xanh lá nhạt.
                "Meeting" -> Color(0xFFBBDEFB) // Xanh dương nhạt.
                else -> Color.LightGray // Mặc định màu xám nhạt cho các trường hợp khác.
            }
        }
        else -> Color.LightGray // Màu xám nhạt mặc định cho các trạng thái khác.
    }

    // Xác định màu chữ và icon dựa trên trạng thái "In Progress".
    val textColor = if (task.status == "In Progress") Color.White else Color.Black
    val descriptionColor = if (task.status == "In Progress") Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.8f)
    val iconTint = if (task.status == "In Progress") Color.White else Color.Black.copy(alpha = 0.6f)

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp) // Đặt khoảng đệm xung quanh thẻ.
            .fillMaxWidth() // Chiếm toàn bộ chiều rộng có sẵn.
            .clickable { onClick(task.id) }, // Xử lý sự kiện nhấp vào thẻ.
        colors = CardDefaults.cardColors(containerColor = cardColor), // Thiết lập màu nền của thẻ.
        shape = RoundedCornerShape(12.dp), // Thiết lập bo góc cho thẻ.
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Thiết lập đổ bóng cho thẻ.
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Đặt khoảng đệm bên trong hàng.
            verticalAlignment = Alignment.CenterVertically // Căn giữa các mục theo chiều dọc.
        ) {
            // Hiển thị icon dựa trên trạng thái công việc.
            Icon(
                imageVector = if (task.status == "In Progress") Icons.Default.CheckCircle else Icons.Default.Square,
                contentDescription = "Status",
                tint = iconTint, // Màu của icon.
                modifier = Modifier.size(28.dp) // Kích thước của icon.
            )

            Spacer(modifier = Modifier.width(16.dp)) // Khoảng trống ngang.

            Column(modifier = Modifier.weight(1f)) { // Cột chứa thông tin văn bản, chiếm phần còn lại của không gian.
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), // Kiểu chữ cho tiêu đề.
                    color = textColor // Màu chữ tiêu đề.
                )
                Spacer(modifier = Modifier.height(4.dp)) // Khoảng trống dọc.
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium, // Kiểu chữ cho mô tả.
                    color = descriptionColor // Màu chữ mô tả.
                )
                Spacer(modifier = Modifier.height(8.dp)) // Khoảng trống dọc.
                Row(
                    modifier = Modifier.fillMaxWidth(), // Chiếm toàn bộ chiều rộng.
                    horizontalArrangement = Arrangement.SpaceBetween, // Sắp xếp các mục cách đều nhau.
                    verticalAlignment = Alignment.CenterVertically // Căn giữa các mục theo chiều dọc.
                ) {
                    Text(
                        text = "Status: ${task.status}",
                        style = MaterialTheme.typography.bodySmall, // Kiểu chữ cho trạng thái.
                        color = textColor // Màu chữ trạng thái.
                    )
                    Text(
                        text = "${task.time} ${task.date}",
                        style = MaterialTheme.typography.bodySmall, // Kiểu chữ cho thời gian và ngày.
                        color = textColor // Màu chữ thời gian và ngày.
                    )
                }
            }
        }
    }
}