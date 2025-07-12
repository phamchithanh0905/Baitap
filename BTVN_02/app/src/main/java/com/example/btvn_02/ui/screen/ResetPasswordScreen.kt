package com.example.btvn_02.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.btvn_02.R
import com.example.btvn_02.ui.component.AppButton
import com.example.btvn_02.ui.component.AppTextField
import com.example.btvn_02.ui.viewmodel.ResetPasswordViewModel

/**
 * Màn hình cho phép người dùng tạo lại mật khẩu mới.
 *
 * @param viewModel ViewModel quản lý trạng thái nhập liệu.
 * @param onNextClicked Hàm callback khi người dùng nhấn "Next".
 * @param onBackClicked Hàm callback khi người dùng nhấn nút quay lại.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(
    viewModel: ResetPasswordViewModel,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.resetPasswordUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
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
            // Logo trường
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = "UTH Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tên ứng dụng
            Text(
                text = "SmartTasks",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tiêu đề và mô tả
            Text(
                text = "Create new password",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your new password must be different from previously used password.",
                style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Trường nhập mật khẩu mới
            AppTextField(
                value = uiState.newPassword,
                onValueChange = { viewModel.updateNewPassword(it) },
                label = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Trường xác nhận mật khẩu
            AppTextField(
                value = uiState.confirmPassword,
                onValueChange = { viewModel.updateConfirmPassword(it) },
                label = "Confirm Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Kiểm tra tính hợp lệ trước khi cho phép nhấn "Next"
            val isPasswordValid = uiState.newPassword.isNotBlank() &&
                    uiState.newPassword == uiState.confirmPassword

            AppButton(
                text = "Next",
                onClick = onNextClicked,
                enabled = isPasswordValid
            )
        }
    }
}
