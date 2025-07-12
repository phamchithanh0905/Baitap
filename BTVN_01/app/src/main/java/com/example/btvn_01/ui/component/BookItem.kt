package com.example.btvn_01.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.btvn_01.data.model.Book

/**
 * Composable để hiển thị một mục sách trong danh sách (ListView).
 * Bao gồm tiêu đề, ID sách và checkbox chọn sách.
 *
 * @param book Đối tượng Book đại diện cho cuốn sách.
 * @param isChecked Trạng thái được check (đánh dấu) của checkbox. Được điều khiển từ ngoài.
 * @param onCheckedChange Callback khi người dùng thay đổi trạng thái checkbox.
 */
@Composable
fun BookItem(
    book: Book,
    isChecked: Boolean,                // Trạng thái check hiện tại (được truyền vào)
    onCheckedChange: (Boolean) -> Unit // Hàm xử lý khi người dùng tick / bỏ tick
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Phần hiển thị thông tin sách
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "ID: ${book.id}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Nếu muốn hiển thị trạng thái "Đã đọc" / "Chưa đọc" từ thuộc tính isBorrowed:
                /*
                Text(
                    text = if (book.isBorrowed) "Đã đọc" else "Chưa đọc",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (book.isBorrowed)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
                */
            }

            // Checkbox cho phép chọn / bỏ chọn sách
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}
