package com.example.baith2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.baith2.navigation.AppScreen
import com.example.baith2.ui.AppNavigation
import com.example.baith2.ui.theme.BaiTH2Theme
import kotlinx.coroutines.delay

/**
 * MainActivity là điểm khởi động chính của ứng dụng Android.
 * Tại đây sử dụng Jetpack Compose để render giao diện, áp dụng theme và điều hướng.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiTH2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

/**
 * Hàm composable chính khởi chạy toàn bộ giao diện người dùng của ứng dụng.
 * Quản lý NavController và thực hiện chuyển từ Splash → Onboarding sau 2 giây.
 */
@Composable
fun MainApp() {
    val navController = rememberNavController()

    // Chuyển màn hình từ Splash sang Onboarding sau khi delay
    LaunchedEffect(Unit) {
        delay(5000)
        navController.navigate(AppScreen.Onboarding.route) {
            popUpTo(AppScreen.Splash.route) { inclusive = true }
        }
    }

    // Giao diện ứng dụng được quản lý thông qua hệ thống điều hướng
    AppNavigation(navController = navController)
}
