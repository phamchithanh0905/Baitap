package com.example.btvn_02.ui.tasklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.btvn_02.data.model.Task
import com.example.btvn_02.data.model.getColor
import com.example.btvn_02.ui.theme.BTVN_02Theme


// Composable cho màn hình Task List
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navController: NavController, // NavController để điều hướng
    viewModel: TaskListViewModel = viewModel(), // ViewModel cho màn hình này
    onAddTaskClick: () -> Unit // Callback khi click nút thêm
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("List", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }, // Tiêu đề TopAppBar
                navigationIcon = {
                    IconButton(onClick = { /* Handle back or profile */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onAddTaskClick) { // Nút thêm mới
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add New",
                            tint = Color.Red // Màu đỏ như trong ảnh
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface // Màu nền TopAppBar
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surface, // Màu nền BottomAppBar
                modifier = Modifier.height(60.dp) // Chiều cao BottomAppBar
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* Home */ }) {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Black)
                    }
                    IconButton(onClick = { /* Calendar */ }) {
                        Icon(Icons.Default.Info, contentDescription = "Calendar", tint = Color.Black)
                    }
                    // Placeholder for FAB, FAB will be rendered on top
                    Spacer(modifier = Modifier.size(56.dp))
                    IconButton(onClick = { /* List */ }) {
                        Icon(Icons.Default.List, contentDescription = "List", tint = Color.Black)
                    }
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.Black)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTaskClick, // Gọi callback khi nhấn FAB
                containerColor = Color(0xFF64B5F6), // Màu FAB
                shape = CircleShape, // Hình tròn
                modifier = Modifier.offset(y = 30.dp) // Đẩy FAB lên cao một chút so với BottomAppBar
            ) {
                Icon(Icons.Default.Add, "Add new task", tint = Color.White)
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center
    ) { paddingValues ->
        TaskListContent(
            paddingValues = paddingValues,
            tasks = viewModel.tasks
        )
    }
}

@Composable
fun TaskListContent(
    paddingValues: PaddingValues, // Padding từ Scaffold
    tasks: List<Task> // Danh sách các công việc
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues) // Áp dụng padding từ Scaffold
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 8.dp) // Padding tổng thể cho nội dung
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp) // Khoảng cách giữa các item
        ) {
            items(tasks) { task ->
                TaskItem(task = task) // Hiển thị từng công việc
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), // ✅ Chiều cao tự động theo nội dung
        colors = CardDefaults.cardColors(
            containerColor = task.status.getColor()
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = task.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = task.description,
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    BTVN_02Theme {
        TaskListScreen(
            navController = rememberNavController(),
            onAddTaskClick = {}
        )
    }
}