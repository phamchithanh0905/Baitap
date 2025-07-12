package com.example.btvn_01.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.btvn_01.data.model.Student

/**
 * Composable hiển thị tên sinh viên hiện tại và cho phép chọn lại sinh viên khác từ danh sách.
 *
 * @param currentStudent Sinh viên đang được chọn (hiện tại).
 * @param students Danh sách tất cả sinh viên.
 * @param onStudentSelected Callback gọi khi người dùng chọn một sinh viên mới.
 * @param modifier Modifier tùy chỉnh bố cục nếu cần.
 */
@Composable
fun StudentSelector(
    currentStudent: Student?,
    students: List<Student>,
    onStudentSelected: (Student) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    // Giao diện hiển thị tên sinh viên và nút "Thay đổi"
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currentStudent?.name ?: "Chọn Sinh Viên",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = { showDialog = true }
        ) {
            Text(text = "Thay đổi")
        }
    }

    // Hiển thị dialog chọn sinh viên
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Chọn sinh viên") },
            text = {
                Column {
                    students.forEach { student ->
                        Text(
                            text = student.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onStudentSelected(student)
                                    showDialog = false
                                }
                                .padding(12.dp)
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Đóng")
                }
            }
        )
    }
}
