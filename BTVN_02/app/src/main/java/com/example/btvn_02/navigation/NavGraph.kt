package com.example.btvn_02.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btvn_02.data.repository.ResetPasswordRepository
import com.example.btvn_02.ui.screen.ConfirmScreen
import com.example.btvn_02.ui.screen.ForgotPasswordScreen
import com.example.btvn_02.ui.screen.ResetPasswordScreen
import com.example.btvn_02.ui.screen.VerifyCodeScreen
import com.example.btvn_02.ui.viewmodel.ResetPasswordViewModel

/**
 * Định nghĩa các tuyến đường (route) đại diện cho từng màn hình trong flow khôi phục mật khẩu.
 */
sealed class Screen(val route: String) {
    object ForgotPassword : Screen("forgot_password_screen")
    object VerifyCode : Screen("verify_code_screen")
    object ResetPassword : Screen("reset_password_screen")
    object Confirm : Screen("confirm_screen")
}

/**
 * Thiết lập đồ thị điều hướng (NavGraph) cho quá trình khôi phục mật khẩu,
 * bao gồm các bước: Quên mật khẩu → Xác thực → Đặt lại mật khẩu → Xác nhận.
 *
 * @param navController Bộ điều hướng điều khiển luồng chuyển màn hình
 * @param repository Repository dùng để lưu trữ dữ liệu người dùng trong quá trình khôi phục
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    repository: ResetPasswordRepository
) {
    // ViewModel được khởi tạo bằng Factory với repository truyền vào
    val viewModel: ResetPasswordViewModel = viewModel(
        factory = ResetPasswordViewModel.Factory(repository)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.ForgotPassword.route
    ) {
        // 1. Màn hình nhập email để bắt đầu quá trình
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                viewModel = viewModel,
                onNextClicked = {
                    navController.navigate(Screen.VerifyCode.route)
                }
            )
        }

        // 2. Màn hình nhập mã xác thực được gửi qua email
        composable(Screen.VerifyCode.route) {
            VerifyCodeScreen(
                viewModel = viewModel,
                onNextClicked = {
                    navController.navigate(Screen.ResetPassword.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // 3. Màn hình nhập mật khẩu mới và xác nhận lại
        composable(Screen.ResetPassword.route) {
            ResetPasswordScreen(
                viewModel = viewModel,
                onNextClicked = {
                    navController.navigate(Screen.Confirm.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }

        // 4. Màn hình thông báo hoàn tất
        composable(Screen.Confirm.route) {
            ConfirmScreen(
                viewModel = viewModel,
                onBackToStartClicked = {
                    viewModel.resetAllInput()
                    navController.popBackStack(Screen.ForgotPassword.route, inclusive = false)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}
