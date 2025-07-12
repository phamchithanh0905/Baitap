package com.example.btvn_01.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview // Thêm import này để xem trước UI
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Thêm import này cho kích thước font
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.btvn_01.R // Đảm bảo import đúng R class của dự án bạn
import com.example.btvn_01.ui.theme.Btvn_01Theme // Import theme của bạn

/**
 * Màn hình hiển thị trạng thái danh sách trống, với icon và thông báo "No Tasks Yet!".
 * Bao gồm TopAppBar với nút Back và tiêu đề "List" giống như trong ảnh.
 *
 * @param navController [NavController] để xử lý sự kiện nút Back.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            // TopAppBar với nút Back và tiêu đề "List"
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "List",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
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
                .background(MaterialTheme.colorScheme.background), // Màu nền tổng thể
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Căn giữa toàn bộ nội dung theo chiều dọc và ngang
        ) {
            // Card chứa nội dung "No Tasks Yet!"
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f) // Chiếm 90% chiều rộng màn hình, khớp với ảnh
                    .wrapContentHeight() // Chiều cao tự điều chỉnh theo nội dung
                    .padding(horizontal = 0.dp), // Padding này đã được xử lý bởi fillMaxWidth(0.9f) và padding của Column cha
                shape = RoundedCornerShape(12.dp), // Bo góc cho Card, khớp với ảnh
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)), // Màu nền xám nhạt cho Card, khớp với ảnh
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Đổ bóng nhẹ cho Card
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp, horizontal = 24.dp), // Padding bên trong Card để tạo khoảng trống lớn, khớp với ảnh
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Icon Clipboard Sleepy
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clipboard_sleepy), // Biểu tượng clipboard đang ngủ
                        contentDescription = "No Tasks",
                        modifier = Modifier.size(96.dp), // Kích thước icon lớn, khớp với ảnh
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f) // Màu icon xám hơn một chút để khớp ảnh
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa icon và tiêu đề

                    // Text "No Tasks Yet!"
                    Text(
                        text = "No Tasks Yet!",
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp), // Điều chỉnh fontSize để khớp hơn
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách giữa tiêu đề và mô tả

                    // Text "Stay productive—add something to do"
                    Text(
                        text = "Stay productive—add something to do",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp), // Điều chỉnh fontSize để khớp hơn
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f) // Màu chữ xám hơn một chút
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyListScreenPreview() {
    Btvn_01Theme {
        EmptyListScreen(navController = rememberNavController())
    }
}