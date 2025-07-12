package com.example.btvn_02.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.btvn_02.R
import com.example.btvn_02.ui.component.AppButton
import com.example.btvn_02.ui.component.AppTextField
import com.example.btvn_02.ui.viewmodel.ResetPasswordViewModel

/**
 * Màn hình "Quên mật khẩu" – nơi người dùng nhập email để nhận mã xác thực.
 *
 * @param viewModel ViewModel quản lý trạng thái khôi phục mật khẩu.
 * @param onNextClicked Hàm callback khi người dùng nhấn nút "Next".
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: ResetPasswordViewModel,
    onNextClicked: () -> Unit
) {
    val uiState by viewModel.forgotPasswordUiState.collectAsState()
    val confirmState by viewModel.confirmUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {} // Không hiển thị tiêu đề
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo trường UTH
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = "UTH Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tiêu đề ứng dụng
            Text(
                text = "SmartTasks",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Phần hướng dẫn
            Text(
                text = "Forget Password?",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your Email, we will send you a verification code.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Trường nhập email
            AppTextField(
                value = uiState.email,
                onValueChange = { viewModel.updateEmail(it) },
                label = "Your Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nút chuyển sang bước tiếp theo
            AppButton(
                text = "Next",
                onClick = onNextClicked,
                enabled = uiState.email.isNotBlank()
            )

            // Nếu quay lại từ màn hình xác nhận, hiển thị thông tin trước đó
            if (
                confirmState.email.isNotBlank() &&
                confirmState.verificationCode.isNotBlank() &&
                confirmState.newPassword.isNotBlank()
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Previously Entered Information:",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Email: ${confirmState.email}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Verification Code: ${confirmState.verificationCode}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "New Password: ${confirmState.newPassword}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
