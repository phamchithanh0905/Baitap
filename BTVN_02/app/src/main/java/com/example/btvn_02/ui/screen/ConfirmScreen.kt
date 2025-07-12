package com.example.btvn_02.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.btvn_02.R
import com.example.btvn_02.ui.component.AppButton
import com.example.btvn_02.ui.component.AppTextField
import com.example.btvn_02.ui.viewmodel.ResetPasswordViewModel

/**
 * Màn hình xác nhận sau khi hoàn tất quá trình đặt lại mật khẩu.
 *
 * @param viewModel ViewModel chứa trạng thái dữ liệu người dùng.
 * @param onBackToStartClicked Hành động khi người dùng muốn quay lại bước đầu.
 * @param onBackClicked Hành động khi nhấn nút quay lại ở thanh công cụ.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(
    viewModel: ResetPasswordViewModel,
    onBackToStartClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.confirmUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo trường UTH
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = "UTH Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tiêu đề chính
            Text(
                text = "Confirm",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Phụ đề và mô tả
            Text(
                text = "Verify Code",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "We are here to help you!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị thông tin đã nhập (readonly)
            AppTextField(
                value = uiState.email,
                onValueChange = {},
                label = "Email",
                enabled = false,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                value = uiState.verificationCode,
                onValueChange = {},
                label = "Verification Code",
                enabled = false,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_code),
                        contentDescription = "Code Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                value = uiState.newPassword,
                onValueChange = {},
                label = "New Password",
                enabled = false,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppTextField(
                value = uiState.confirmPassword,
                onValueChange = {},
                label = "Confirm Password",
                enabled = false,
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

            // Nút gửi xác nhận hoàn tất
            AppButton(
                text = "Submit",
                onClick = onBackToStartClicked
            )
        }
    }
}
