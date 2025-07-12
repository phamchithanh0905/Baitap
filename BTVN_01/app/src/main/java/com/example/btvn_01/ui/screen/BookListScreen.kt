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
import com.example.btvn_01.data.model.Book
import com.example.btvn_01.data.repository.LibraryRepository
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavController, libraryRepository: LibraryRepository) {
    val availableBookTemplates by libraryRepository.availableBookTemplates.collectAsState()
    val currentStudent by libraryRepository.currentStudent.collectAsState()

    var showAddTemplateDialog by remember { mutableStateOf(false) }
    var newTemplateTitleInput by remember { mutableStateOf("") }

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
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
            if (availableBookTemplates.isEmpty()) {
                Text(
                    text = "Không có mẫu sách nào trong danh mục để thêm.\nNhấn 'Thêm Sách' để tạo mẫu mới.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(availableBookTemplates) { bookTemplate ->
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
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = bookTemplate.title,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = "Mẫu ID: ${bookTemplate.id}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                Button(
                                    onClick = {
                                        currentStudent?.let { student ->
                                            val newBookId = "book_${student.id}_${(student.personalBooks.size + 1)}"
                                            val newBook = Book(
                                                id = newBookId,
                                                title = bookTemplate.title,
                                                isBorrowed = false
                                            )
                                            libraryRepository.addBookForStudent(student.id, newBook)
                                            libraryRepository.setCurrentStudent(student.id) // Trigger lại UI
                                            navController.popBackStack()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    enabled = currentStudent != null
                                ) {
                                    Text("Thêm vào ${currentStudent?.name ?: "Sinh viên"}")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showAddTemplateDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Thêm Sách", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (currentStudent == null) {
                Text(
                    text = "Vui lòng chọn một sinh viên ở màn hình 'Sinh viên' để thêm sách vào danh sách cá nhân của họ.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        if (showAddTemplateDialog) {
            AlertDialog(
                onDismissRequest = {
                    showAddTemplateDialog = false
                    newTemplateTitleInput = ""
                },
                title = { Text("Thêm Mẫu Sách Mới") },
                text = {
                    Column {
                        Text("Nhập tên mẫu sách:")
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = newTemplateTitleInput,
                            onValueChange = { newTemplateTitleInput = it },
                            label = { Text("Tên mẫu sách") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newTemplateTitleInput.isNotBlank()) {
                                val newTemplateId = "template_${(availableBookTemplates.size + 1).toString().padStart(3, '0')}"
                                val newBookTemplate = Book(
                                    id = newTemplateId,
                                    title = newTemplateTitleInput.trim(),
                                    isBorrowed = false
                                )
                                libraryRepository.addAvailableBookTemplate(newBookTemplate)
                                newTemplateTitleInput = ""
                                showAddTemplateDialog = false
                            }
                        }
                    ) {
                        Text("Thêm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        newTemplateTitleInput = ""
                        showAddTemplateDialog = false
                    }) {
                        Text("Hủy")
                    }
                }
            )
        }
    }
}
