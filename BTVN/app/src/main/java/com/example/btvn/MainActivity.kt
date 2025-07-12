package com.example.btvn

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.btvn.navigation.NavGraph
import com.example.btvn.ui.theme.BTVNTheme
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.AndroidEntryPoint

/**
 * Đánh dấu lớp Application để Hilt tự động cấu hình Dependency Injection.
 */
@HiltAndroidApp
class BTVNApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Có thể khởi tạo Firebase hoặc các dịch vụ toàn cục tại đây.
    }
}

/**
 * Màn hình chính của ứng dụng.
 * Dùng @AndroidEntryPoint để Hilt có thể inject các thành phần cần thiết.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTVNTheme {
                // Thiết lập nền và màu theo theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Khởi tạo bộ điều hướng và vẽ màn hình theo NavGraph
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
