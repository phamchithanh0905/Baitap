package com.example.btvn_01.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.btvn_01.R
import com.example.btvn_01.data.model.Task
import com.example.btvn_01.viewmodel.TaskViewModel

/**
 * Màn hình hiển thị chi tiết của một công việc.
 *
 * @param taskId ID của công việc cần hiển thị chi tiết.
 * @param navController [NavController] để điều hướng giữa các màn hình.
 * @param taskViewModel [TaskViewModel] để quản lý dữ liệu và trạng thái của công việc.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    navController: NavController,
    taskViewModel: TaskViewModel = viewModel()
) {
    // Thu thập trạng thái từ ViewModel
    val selectedTask by taskViewModel.selectedTask.collectAsState()
    val isLoading by taskViewModel.detailIsLoading.collectAsState()
    val errorMessage by taskViewModel.detailErrorMessage.collectAsState()
    val deleteMessage by taskViewModel.deleteMessage.collectAsState()

    // Lấy chi tiết công việc khi màn hình được khởi tạo hoặc taskId thay đổi
    LaunchedEffect(taskId) {
        taskViewModel.fetchTaskDetails(taskId)
    }

    // Xử lý thông báo xóa công việc
    LaunchedEffect(deleteMessage) {
        deleteMessage?.let { message ->
            if (message.contains("success", ignoreCase = true)) {
                navController.popBackStack() // Quay lại màn hình trước đó nếu xóa thành công
            }
            taskViewModel.clearDeleteMessage() // Xóa thông báo sau khi xử lý
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Gọi hàm xóa công việc từ ViewModel
                        taskViewModel.deleteTask(taskId)
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()), // Cho phép cuộn màn hình
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else if (errorMessage != null) {
                Text(
                    text = "Error: $errorMessage",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = { taskViewModel.fetchTaskDetails(taskId) }) {
                    Text("Retry")
                }
            } else if (selectedTask != null) {
                val task = selectedTask!!
                Spacer(modifier = Modifier.height(16.dp))

                // Tiêu đề và mô tả công việc
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Thẻ thông tin danh mục, trạng thái, ưu tiên
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8BBD0)) // Nền hồng nhạt
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TaskInfoItem(
                            iconRes = R.drawable.ic_category,
                            label = "Category",
                            value = task.category
                        )
                        TaskInfoItem(
                            iconRes = R.drawable.ic_status,
                            label = "Status",
                            value = task.status
                        )
                        TaskInfoItem(
                            iconRes = R.drawable.ic_priority,
                            label = "Priority",
                            value = task.priority
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Phần công việc con (Subtasks)
                task.subtasks?.takeIf { it.isNotEmpty() }?.let { subtasks ->
                    Text(
                        text = "Subtasks",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    subtasks.forEach { subtask ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = subtask.isCompleted,
                                onCheckedChange = { /* Xử lý thay đổi trạng thái công việc con */ },
                                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = subtask.title,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Phần tệp đính kèm (Attachments)
                task.attachments?.takeIf { it.isNotEmpty() }?.let { attachments ->
                    Text(
                        text = "Attachments",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    attachments.forEach { attachment ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_attachment),
                                contentDescription = "Attachment",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = attachment.fileName,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Phần nhắc nhở (Reminders)
                task.reminders?.takeIf { it.isNotEmpty() }?.let { reminders ->
                    Text(
                        text = "Reminders",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    reminders.forEach { reminder ->
                        Text(
                            text = "- ${reminder.time} (${reminder.type})",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

            } else {
                // Hiển thị khi không tìm thấy công việc
                Text("Task not found.", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

/**
 * Composable dùng để hiển thị một mục thông tin công việc (ví dụ: Category, Status, Priority).
 *
 * @param iconRes ID tài nguyên của biểu tượng.
 * @param label Nhãn của mục thông tin (ví dụ: "Category").
 * @param value Giá trị của mục thông tin (ví dụ: "Work").
 */
@Composable
fun TaskInfoItem(iconRes: Int, label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f))
        Text(text = value, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSecondaryContainer)
    }
}