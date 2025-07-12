package com.example.btvn_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.btvn_02.data.repository.ResetPasswordRepository
import com.example.btvn_02.navigation.NavGraph
import com.example.btvn_02.ui.theme.BTVN_02Theme

/**
 * MainActivity là điểm khởi đầu của ứng dụng.
 * Tại đây khởi tạo giao diện và hệ thống điều hướng Compose.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kích hoạt hiển thị toàn màn hình (ẩn thanh trạng thái và thanh điều hướng nếu có)
        enableEdgeToEdge()

        setContent {
            // Áp dụng giao diện theo chủ đề Material 3
            BTVN_02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Giao diện điều hướng chính của ứng dụng
                    ResetPasswordApp()
                }
            }
        }
    }
}

/**
 * Composable chứa toàn bộ luồng điều hướng của ứng dụng,
 * bao gồm khởi tạo NavController và Repository.
 */
@Composable
fun ResetPasswordApp() {
    // Tạo NavController để quản lý điều hướng giữa các màn hình
    val navController = rememberNavController()

    // Khởi tạo Repository để chia sẻ dữ liệu người dùng giữa các màn hình
    val repository = ResetPasswordRepository()

    // Gọi NavGraph định nghĩa các tuyến màn hình
    NavGraph(navController = navController, repository = repository)
}
