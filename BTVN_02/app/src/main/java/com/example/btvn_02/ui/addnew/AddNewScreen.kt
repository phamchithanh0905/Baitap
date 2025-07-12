package com.example.btvn_02.ui.addnew

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.btvn_02.data.model.Task
import com.example.btvn_02.ui.theme.BTVN_02Theme

// Composable cho màn hình "Add New Task"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewScreen(
    navController: NavController, // NavController để điều hướng
    onAddTask: (Task) -> Unit, // Callback khi thêm công việc
    addNewViewModel: AddNewViewModel = viewModel() // ViewModel cho màn hình này
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Add New", color = Color.Black, fontWeight = FontWeight.Bold)
                }, // Tiêu đề TopAppBar
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Nút quay lại
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface // Màu nền TopAppBar
                )
            )
        },
        content = { paddingValues ->
            AddNewContent(
                paddingValues = paddingValues,
                addNewViewModel = addNewViewModel,
                onAddTask = onAddTask,
                onBack = { navController.popBackStack() } // Khi thêm xong hoặc hủy thì quay lại
            )
        }
    )
}

@Composable
fun AddNewContent(
    paddingValues: PaddingValues, // Padding từ Scaffold
    addNewViewModel: AddNewViewModel, // ViewModel
    onAddTask: (Task) -> Unit, // Callback thêm công việc
    onBack: () -> Unit // Callback quay lại
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues) // Áp dụng padding từ Scaffold
            .padding(16.dp), // Padding tổng thể cho nội dung
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // TextField cho tiêu đề công việc
        OutlinedTextField(
            value = addNewViewModel.taskTitle,
            onValueChange = { addNewViewModel.updateTaskTitle(it) },
            label = { Text("Task") }, // Nhãn của TextField
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp), // Bo tròn góc
            singleLine = true // Chỉ cho phép một dòng
        )
        Spacer(modifier = Modifier.height(16.dp))

        // TextField cho mô tả công việc
        OutlinedTextField(
            value = addNewViewModel.taskDescription,
            onValueChange = { addNewViewModel.updateTaskDescription(it) },
            label = { Text("Description") }, // Nhãn của TextField
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp), // Bo tròn góc
            minLines = 3 // Cho phép nhiều dòng cho mô tả
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Nút "Add"
        Button(
            onClick = {
                if (addNewViewModel.taskTitle.isNotBlank() && addNewViewModel.taskDescription.isNotBlank()) {
                    val newTask = addNewViewModel.createTask() // Tạo công việc mới
                    onAddTask(newTask) // Gọi callback để thêm vào danh sách
                    addNewViewModel.resetFields() // Reset các trường
                    onBack() // Quay lại màn hình trước
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6)), // Màu nút Add
            shape = RoundedCornerShape(8.dp) // Bo tròn góc
        ) {
            Text("Add", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewScreenPreview() {
    BTVN_02Theme {
        AddNewScreen(
            navController = rememberNavController(),
            onAddTask = {}
        )
    }
}