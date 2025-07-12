package com.example.btvn_01.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btvn_01.data.repository.LibraryRepository
import com.example.btvn_01.ui.screen.BookListScreen
import com.example.btvn_01.ui.screen.HomeScreen
import com.example.btvn_01.ui.screen.StudentListScreen

/**
 * sealed class đại diện cho các màn hình (Screen) trong ứng dụng.
 * Mỗi object định nghĩa một tuyến đường (route) duy nhất.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object BookList : Screen("book_list_screen")
    object StudentList : Screen("student_list_screen")
}

/**
 * Hàm NavGraph định nghĩa sơ đồ điều hướng (navigation graph) cho toàn bộ ứng dụng.
 *
 * @param navController Điều khiển điều hướng giữa các màn hình.
 * @param libraryRepository Repository dùng chung, truyền vào cho các màn hình cần thao tác dữ liệu.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    libraryRepository: LibraryRepository
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Màn hình chính: Quản lý sách cho sinh viên đang chọn
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                libraryRepository = libraryRepository
            )
        }

        // Màn hình danh sách mẫu sách có thể thêm
        composable(Screen.BookList.route) {
            BookListScreen(
                navController = navController,
                libraryRepository = libraryRepository
            )
        }

        // Màn hình danh sách sinh viên để chọn hoặc chỉnh sửa
        composable(Screen.StudentList.route) {
            StudentListScreen(
                navController = navController,
                libraryRepository = libraryRepository
            )
        }
    }
}
