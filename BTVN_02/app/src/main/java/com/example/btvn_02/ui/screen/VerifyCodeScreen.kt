package com.example.btvn_02.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.btvn_02.R
import com.example.btvn_02.ui.component.AppButton
import com.example.btvn_02.ui.component.AppTextField
import com.example.btvn_02.ui.viewmodel.ResetPasswordViewModel

/**
 * Màn hình nhập mã xác thực gồm 5 chữ số được gửi về email người dùng.
 *
 * @param viewModel ViewModel chứa trạng thái nhập mã xác thực.
 * @param onNextClicked Hàm callback khi người dùng nhấn "Next".
 * @param onBackClicked Hàm callback khi người dùng nhấn quay lại.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyCodeScreen(
    viewModel: ResetPasswordViewModel,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.verifyCodeUiState.collectAsState()

    // Danh sách 5 FocusRequesters để tự động chuyển ô nhập
    val focusRequesters = remember { List(5) { FocusRequester() } }

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
                .padding(horizontal = 16.dp),
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

            // Tiêu đề và hướng dẫn
            Text(
                text = "Verify Code",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter the code we just sent you on your registered Email.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hàng chứa 5 ô nhập mã xác thực
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    val digit = uiState.verificationCode.getOrNull(index)?.toString() ?: ""
                    AppTextField(
                        value = digit,
                        onValueChange = { newValue ->
                            val current = uiState.verificationCode.padEnd(5, ' ')
                            val builder = StringBuilder(current)

                            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                if (newValue.isNotEmpty()) {
                                    builder.setCharAt(index, newValue[0])
                                    viewModel.updateVerificationCode(builder.toString().trimEnd())
                                    if (index < 4) focusRequesters[index + 1].requestFocus()
                                } else {
                                    builder.setCharAt(index, ' ')
                                    viewModel.updateVerificationCode(builder.toString().trimEnd())
                                    if (index > 0) focusRequesters[index - 1].requestFocus()
                                }
                            }
                        },
                        label = "",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = if (index == 4) ImeAction.Done else ImeAction.Next
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .focusRequester(focusRequesters[index]),
                        visualTransformation = VisualTransformation.None,
                        maxLines = 1,
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Nút chuyển bước nếu đã đủ 5 ký tự
            AppButton(
                text = "Next",
                onClick = onNextClicked,
                enabled = uiState.verificationCode.length == 5
            )
        }
    }
}
