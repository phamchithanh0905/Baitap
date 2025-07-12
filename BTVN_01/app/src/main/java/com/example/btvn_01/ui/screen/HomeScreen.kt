package com.example.btvn_01.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.btvn_01.data.repository.LibraryRepository
import com.example.btvn_01.navigation.Screen
import com.example.btvn_01.ui.component.BookItem
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import com.example.btvn_01.data.model.Book // Import Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, libraryRepository: LibraryRepository) {
    val currentStudent by libraryRepository.currentStudent.collectAsState()
    val allStudents by libraryRepository.students.collectAsState() // Vẫn cần để tạo ID sinh viên mới nếu có

    val personalBookCheckedStates = remember(currentStudent) {
        val initialMap = mutableStateMapOf<String, Boolean>()
        currentStudent?.personalBooks?.forEach { book ->
            initialMap[book.id] = book.isBorrowed
        }
        initialMap
    }

    var showAddBookDialog by remember { mutableStateOf(false) }
    var newBookTitleInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            // Thay vì CenterAlignedTopAppBar, dùng TopAppBar thông thường
            TopAppBar(
                title = {
                    Text(
                        text = "Hệ thống\nQuản lý Thư viện",
                        modifier = Modifier.fillMaxWidth(), // Chiếm toàn bộ chiều rộng có sẵn
                        textAlign = TextAlign.Center, // Căn giữa nội dung văn bản
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
                // Không có navigationIcon ở đây, nên Text sẽ được căn giữa hoàn hảo
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Phần thông tin Sinh viên
            Text(
                text = "Sinh viên",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 4.dp),
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = currentStudent?.name ?: "Chưa chọn sinh viên",
                    onValueChange = { /* Không cho phép sửa trực tiếp ở đây */ },
                    readOnly = true,
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Button(
                    onClick = {
                        navController.navigate(Screen.StudentList.route)
                    },
                    modifier = Modifier.padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Thay đổi")
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            // Danh sách sách cá nhân của sinh viên
            Text(
                text = "Danh sách sách của ${currentStudent?.name ?: "sinh viên"}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Start
            )
            currentStudent?.let { student ->
                val booksToDisplay = student.personalBooks.sortedBy { it.title }

                if (booksToDisplay.isEmpty()) {
                    Text(
                        text = "${student.name} chưa có sách nào. Nhấn 'Thêm' để thêm sách cá nhân!",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .border(
                                BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        items(booksToDisplay) { book ->
                            val isChecked = personalBookCheckedStates[book.id] ?: book.isBorrowed

                            BookItem(
                                book = book,
                                isChecked = isChecked,
                                onCheckedChange = { newCheckedState ->
                                    libraryRepository.toggleBookBorrowedStatusForStudent(student.id, book.id, newCheckedState)
                                    personalBookCheckedStates[book.id] = newCheckedState
                                    libraryRepository.setCurrentStudent(student.id)
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            } ?: run {
                Text(
                    text = "Vui lòng chọn một sinh viên để quản lý sách cá nhân.",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nút "Thêm Sách Cá nhân"
            Button(
                onClick = {
                    if (currentStudent != null) {
                        showAddBookDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                enabled = currentStudent != null
            ) {
                Text("Thêm", style = MaterialTheme.typography.titleMedium)
            }
        }

        // AlertDialog để thêm sách mới vào sinh viên đang chọn
        if (showAddBookDialog && currentStudent != null) {
            AlertDialog(
                onDismissRequest = {
                    showAddBookDialog = false
                    newBookTitleInput = ""
                },
                title = { Text("Thêm Sách Mới cho ${currentStudent?.name}") },
                text = {
                    Column {
                        Text("Nhập tên sách:")
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = newBookTitleInput,
                            onValueChange = { newBookTitleInput = it },
                            label = { Text("Tên sách") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newBookTitleInput.isNotBlank()) {
                                val newBookId = "book_${currentStudent?.id}_${(currentStudent?.personalBooks?.size ?: 0) + 1}"
                                val newBook = Book(id = newBookId, title = newBookTitleInput.trim(), isBorrowed = false)
                                currentStudent?.let { student ->
                                    libraryRepository.addBookForStudent(student.id, newBook)
                                    libraryRepository.setCurrentStudent(student.id)
                                }
                                newBookTitleInput = ""
                                showAddBookDialog = false
                            }
                        }
                    ) {
                        Text("Thêm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        newBookTitleInput = ""
                        showAddBookDialog = false
                    }) {
                        Text("Hủy")
                    }
                }
            )
        }
    }
}

// Bottom Navigation Bar (không thay đổi)
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.Home, Screen.BookList, Screen.StudentList)
    NavigationBar {
        items.forEach { screen ->
            val isSelected = navController.currentDestination?.route == screen.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val icon = when (screen) {
                        Screen.Home -> Icons.Default.Home
                        Screen.BookList -> Icons.Default.List
                        Screen.StudentList -> Icons.Default.Person
                    }
                    Icon(icon, contentDescription = when (screen) {
                        Screen.Home -> "Quản lý"
                        Screen.BookList -> "DS Sách"
                        Screen.StudentList -> "Sinh viên"
                    })
                },
                label = {
                    Text(when (screen) {
                        Screen.Home -> "Quản lý"
                        Screen.BookList -> "DS Sách"
                        Screen.StudentList -> "Sinh viên"
                    })
                }
            )
        }
    }
}