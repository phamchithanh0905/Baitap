package com.example.btvn_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.btvn_01.data.repository.LibraryRepository
import com.example.btvn_01.navigation.NavGraph
import com.example.btvn_01.ui.theme.Btvn_01Theme

class MainActivity : ComponentActivity() {

    // Khởi tạo LibraryRepository với CoroutineScope là lifecycleScope của Activity
    // Sử dụng `by lazy` đảm bảo chỉ khởi tạo khi được dùng lần đầu
    private val libraryRepository: LibraryRepository by lazy {
        LibraryRepository(applicationScope = lifecycleScope)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Áp dụng chủ đề Material Design
            Btvn_01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Gọi Composable chính để hiển thị toàn bộ ứng dụng
                    LibraryApp(libraryRepository = libraryRepository)
                }
            }
        }
    }
}

@Composable
fun LibraryApp(libraryRepository: LibraryRepository) {
    // Tạo NavController để điều hướng giữa các màn hình
    val navController = rememberNavController()

    // Khởi động NavGraph chứa các màn hình và route
    NavGraph(
        navController = navController,
        libraryRepository = libraryRepository
    )
}
