package com.example.baith2.navigation

/**
 * Lớp sealed đại diện cho các tuyến đường (route) điều hướng trong ứng dụng.
 *
 * Đây là ví dụ cho nguyên lý:
 * - **Trừu tượng hóa (Abstraction)**: gom tất cả các màn hình vào một kiểu chung.
 * - **Đa hình (Polymorphism)**: mỗi màn hình là một đối tượng cụ thể kế thừa từ AppScreen.
 *
 * Mỗi đối tượng con sẽ định nghĩa một route duy nhất để sử dụng trong NavHost.
 */
sealed class AppScreen(val route: String) {

    /**
     * Màn hình khởi động đầu tiên khi mở ứng dụng (Splash Screen).
     */
    object Splash : AppScreen("splash_screen")

    /**
     * Màn hình giới thiệu (Onboarding) gồm nhiều trang hướng dẫn người dùng.
     */
    object Onboarding : AppScreen("onboarding_flow")

    // 🔹 Có thể mở rộng thêm các màn hình khác như sau:
    // object Home : AppScreen("home_screen")
    // object Profile : AppScreen("profile_screen")
    // object TaskDetail : AppScreen("task_detail/{taskId}")
}
