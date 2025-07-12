package com.example.btvn_01.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect // Import LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.btvn_01.R
import com.example.btvn_01.ui.components.TaskCard
import com.example.btvn_01.viewmodel.TaskViewModel

/**
 * Màn hình hiển thị danh sách các công việc.
 *
 * @param navController [NavController] để điều hướng giữa các màn hình.
 * @param taskViewModel [TaskViewModel] để quản lý dữ liệu và trạng thái của danh sách công việc.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navController: NavController,
    taskViewModel: TaskViewModel = viewModel()
) {
    // Thu thập trạng thái từ ViewModel
    val tasks by taskViewModel.tasks.collectAsState()
    val isLoading by taskViewModel.isLoading.collectAsState()
    val errorMessage by taskViewModel.errorMessage.collectAsState()

    // LaunchedEffect sẽ chạy khi các key thay đổi.
    // Điều hướng sẽ xảy ra nếu không loading, không lỗi, và danh sách task rỗng.
    LaunchedEffect(tasks.isEmpty(), isLoading, errorMessage) {
        // Chỉ điều hướng nếu không đang tải, không có lỗi, VÀ danh sách tasks rỗng.
        if (!isLoading && errorMessage == null && tasks.isEmpty()) {
            // Kiểm tra xem đã ở EmptyListScreen chưa để tránh navigate lặp lại
            if (navController.currentDestination?.route != "empty_list_screen") {
                navController.navigate("empty_list_screen") {
                    // Tùy chọn: Xóa TaskListScreen khỏi back stack.
                    // Khi người dùng nhấn Back từ EmptyListScreen, họ sẽ không quay lại TaskListScreen này.
                    popUpTo("task_list") { inclusive = true }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.uthm_logo),
                            contentDescription = "UTHM Logo",
                            modifier = Modifier
                                .size(150.dp)
                                .padding(15.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "SmartTasks",
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "A simple and efficient to-do app",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /* Xử lý thông báo */ }) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Điều hướng đến màn hình thêm công việc */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, "Add new task")
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Xử lý nhấp vào Home */ },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Xử lý nhấp vào Calendar */ },
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
                    label = { Text("Calendar") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Xử lý nhấp vào Notes */ },
                    icon = { Icon(painterResource(id = R.drawable.ic_document), contentDescription = "Notes") },
                    label = { Text("Notes") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Xử lý nhấp vào Settings */ },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
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
                Button(onClick = { taskViewModel.fetchTasks() }) {
                    Text("Retry")
                }
            } else {
                // Chỉ hiển thị LazyColumn nếu có tasks
                if (tasks.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(top = 8.dp, bottom = 80.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(tasks) { task ->
                            TaskCard(task = task) { taskId ->
                                navController.navigate("task_detail/${taskId}")
                            }
                        }
                    }
                }
                // Nếu tasks rỗng, LaunchedEffect sẽ xử lý việc điều hướng.
                // Có thể hiển thị một ProgressBar nhỏ ở đây trong khi chờ điều hướng xảy ra
                else if (tasks.isEmpty() && !isLoading && errorMessage == null) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}