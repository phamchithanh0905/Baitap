package com.example.btvn_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.btvn_01.ui.screens.TaskListScreen
import com.example.btvn_01.ui.screens.TaskDetailScreen
import com.example.btvn_01.ui.screens.EmptyListScreen // Import màn hình EmptyListScreen
import com.example.btvn_01.ui.theme.Btvn_01Theme

/**
 * [MainActivity] là Activity chính của ứng dụng, chịu trách nhiệm thiết lập giao diện người dùng
 * và điều hướng giữa các màn hình sử dụng Jetpack Compose và Navigation Component.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Btvn_01Theme { // Áp dụng chủ đề của ứng dụng.
                Surface(
                    modifier = Modifier.fillMaxSize(), // Chiếm toàn bộ không gian.
                    color = MaterialTheme.colorScheme.background // Màu nền.
                ) {
                    val navController = rememberNavController() // Khởi tạo NavController.

                    // Định nghĩa cấu trúc điều hướng của ứng dụng.
                    // "task_list" là màn hình khởi đầu.
                    NavHost(navController = navController, startDestination = "task_list") {
                        composable("task_list") { // Định nghĩa màn hình danh sách công việc.
                            TaskListScreen(navController = navController)
                        }
                        composable( // Định nghĩa màn hình chi tiết công việc.
                            "task_detail/{taskId}", // Route với đối số taskId.
                            arguments = listOf(navArgument("taskId") { type = NavType.IntType }) // Kiểu đối số.
                        ) { backStackEntry ->
                            val taskId = backStackEntry.arguments?.getInt("taskId") // Lấy taskId.
                            if (taskId != null) {
                                TaskDetailScreen(taskId = taskId, navController = navController)
                            } else {
                                Text("Error: Task ID not provided") // Lỗi nếu không có taskId.
                            }
                        }
                        composable("empty_list_screen") { // Định nghĩa màn hình danh sách trống.
                            EmptyListScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}