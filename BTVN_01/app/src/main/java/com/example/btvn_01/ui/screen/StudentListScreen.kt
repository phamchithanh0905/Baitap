package com.example.btvn_01.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.btvn_01.data.model.Student
import com.example.btvn_01.data.repository.LibraryRepository
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(navController: NavController, libraryRepository: LibraryRepository) {
    val allStudents by libraryRepository.students.collectAsState()

    var showAddStudentDialog by remember { mutableStateOf(false) }
    var newStudentNameInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Hệ thống\nQuản lý Thư viện",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Quay lại")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (allStudents.isEmpty()) {
                Text(
                    text = "Chưa có sinh viên nào trong hệ thống.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(allStudents) { student ->
                        StudentCard(
                            student = student,
                            onSelect = {
                                libraryRepository.setCurrentStudent(student.id)
                                navController.popBackStack()
                            },
                            onDelete = {
                                libraryRepository.deleteStudent(student.id)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nút thêm sinh viên
            Button(
                onClick = { showAddStudentDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Thêm Sinh viên", style = MaterialTheme.typography.titleMedium)
            }
        }

        // Dialog thêm sinh viên mới
        if (showAddStudentDialog) {
            AlertDialog(
                onDismissRequest = {
                    showAddStudentDialog = false
                    newStudentNameInput = ""
                },
                title = { Text("Thêm Sinh viên Mới") },
                text = {
                    Column {
                        Text("Nhập tên sinh viên:")
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = newStudentNameInput,
                            onValueChange = { newStudentNameInput = it },
                            label = { Text("Tên sinh viên") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newStudentNameInput.isNotBlank()) {
                                val newId = "student_${(allStudents.size + 1).toString().padStart(3, '0')}"
                                val newStudent = Student(id = newId, name = newStudentNameInput.trim())
                                libraryRepository.addStudent(newStudent)
                                newStudentNameInput = ""
                                showAddStudentDialog = false
                            }
                        }
                    ) {
                        Text("Thêm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        newStudentNameInput = ""
                        showAddStudentDialog = false
                    }) {
                        Text("Hủy")
                    }
                }
            )
        }
    }
}

@Composable
fun StudentCard(
    student: Student,
    onSelect: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onSelect
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = student.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "ID: ${student.id}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Sách cá nhân: ${student.personalBooks.size} quyển",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Xóa")
            }
        }
    }
}
