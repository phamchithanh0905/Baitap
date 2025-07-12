package com.example.btvn.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btvn.ui.HomeScreen
import com.example.btvn.ui.LoginScreen
import com.example.btvn.ui.ProfileScreen
import com.example.btvn.ui.PhoneAuthScreen // Import PhoneAuthScreen

/**
 * `Screen` là một **sealed class** định nghĩa tất cả các màn hình (destinations)
 * trong ứng dụng. Mỗi màn hình có một `route` (đường dẫn) duy nhất để điều hướng.
 * Việc sử dụng sealed class giúp chúng ta dễ dàng quản lý và tránh lỗi khi định nghĩa các màn hình.
 */
sealed class Screen(val route: String) {
    /**
     * Màn hình đăng nhập.
     */
    object LoginScreen : Screen("login_screen")

    /**
     * Màn hình hồ sơ người dùng.
     */
    object ProfileScreen : Screen("profile_screen")

    /**
     * Màn hình chính của ứng dụng.
     */
    object HomeScreen : Screen("home_screen")

    /**
     * Màn hình xác thực số điện thoại.
     */
    object PhoneAuthScreen : Screen("phone_auth_screen")
}

/**
 * Hàm `NavGraph` thiết lập cấu trúc điều hướng cho ứng dụng sử dụng Jetpack Compose Navigation.
 * Nó định nghĩa các màn hình có thể truy cập và cách điều hướng giữa chúng.
 *
 * @param navController Bộ điều khiển điều hướng, quản lý việc chuyển đổi giữa các màn hình.
 */
@Composable
fun NavGraph(navController: NavHostController) {
    // NavHost là một Composable chịu trách nhiệm điều hướng giữa các màn hình.
    NavHost(
        navController = navController, // Bộ điều khiển điều hướng được sử dụng.
        startDestination = Screen.LoginScreen.route // Màn hình khởi đầu khi ứng dụng được mở.
    ) {
        // Định nghĩa màn hình đăng nhập.
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController) // Hiển thị LoginScreen.
        }
        // Định nghĩa màn hình hồ sơ.
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController) // Hiển thị ProfileScreen.
        }
        // Định nghĩa màn hình chính.
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController) // Hiển thị HomeScreen.
        }
        // Định nghĩa màn hình xác thực số điện thoại.
        composable(Screen.PhoneAuthScreen.route) {
            PhoneAuthScreen(navController = navController) // Hiển thị PhoneAuthScreen.
        }
    }
}