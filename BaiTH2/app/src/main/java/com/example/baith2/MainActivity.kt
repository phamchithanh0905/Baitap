package com.example.baith2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.baith2.ui.ProductScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppWithTopBar() // Hiển thị ứng dụng với TopBar
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppWithTopBar() {
    Scaffold(
        topBar = {
            // Thanh tiêu đề ở giữa màn hình
            CenterAlignedTopAppBar(
                title = {
                    Text("Product detail") // Tiêu đề
                },
                navigationIcon = {
                    // Nút quay lại
                    IconButton(onClick = {
                        // Xử lý sự kiện khi nhấn nút Back
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White, // Màu nền của TopBar
                    titleContentColor = Color.Black // Màu chữ tiêu đề
                )
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            ProductScreen() // Hiển thị màn hình sản phẩm
        }
    }
}