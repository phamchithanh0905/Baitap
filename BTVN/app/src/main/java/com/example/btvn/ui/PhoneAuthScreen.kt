package com.example.btvn.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.btvn.model.AuthResponse
import com.example.btvn.navigation.Screen
import com.example.btvn.viewmodel.AuthViewModel

/**
 * Hàm tiện ích để tìm và trả về [Activity] từ một [Context].
 * Rất hữu ích khi cần Context của Activity cho các API yêu cầu nó (ví dụ: Firebase Phone Auth).
 */
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this // Nếu Context đã là Activity, trả về chính nó.
    is ContextWrapper -> baseContext.findActivity() // Nếu là ContextWrapper, tìm Activity từ baseContext.
    else -> null // Không tìm thấy Activity.
}

/**
 * Màn hình xác thực số điện thoại.
 * Cho phép người dùng nhập số điện thoại để nhận mã OTP và sau đó nhập mã để xác minh.
 * Sau khi xác minh thành công, sẽ điều hướng đến màn hình hồ sơ.
 *
 * @param navController Bộ điều khiển điều hướng.
 * @param authViewModel ViewModel chứa logic xác thực số điện thoại, được Hilt cung cấp.
 */
@OptIn(ExperimentalMaterial3Api::class) // Cần để sử dụng OutlinedTextField và CenterAlignedTopAppBar
@Composable
fun PhoneAuthScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel() // Khởi tạo ViewModel bằng Hilt
) {
    val context = LocalContext.current // Lấy Context hiện tại
    val activity = context.findActivity() // Tìm Activity từ Context, cần cho Phone Auth

    // Trạng thái cho các trường nhập liệu
    var phoneNumber by remember { mutableStateOf("") }
    var verificationCode by remember { mutableStateOf("") } // Cho mã OTP
    val authStatus by authViewModel.authStatus.collectAsState() // Lắng nghe trạng thái xác thực từ ViewModel

    // Effect để theo dõi và phản ứng với các thay đổi trạng thái xác thực.
    // Xử lý điều hướng và hiển thị Toast dựa trên kết quả xác thực.
    LaunchedEffect(authStatus) {
        when (val status = authStatus) {
            is AuthResponse.Success -> {
                // Khi xác thực điện thoại thành công, hiển thị Toast
                Toast.makeText(context, "Phone verification successful!", Toast.LENGTH_SHORT).show()
                // Điều hướng đến ProfileScreen
                navController.navigate(Screen.ProfileScreen.route) {
                    // Xóa back stack về LoginScreen để ngăn người dùng quay lại màn hình xác thực
                    popUpTo(Screen.LoginScreen.route) { inclusive = true }
                }
                authViewModel.resetAuthStatus() // Đặt lại trạng thái ViewModel để tránh điều hướng lặp lại
            }
            is AuthResponse.Error -> {
                // Khi có lỗi, hiển thị thông báo lỗi
                Toast.makeText(context, "Phone verification failed: ${status.errorMessage}", Toast.LENGTH_LONG).show()
                authViewModel.resetAuthStatus() // Đặt lại trạng thái ViewModel
            }
            AuthResponse.Loading -> {
                // Trạng thái đang tải: UI (nút, thanh tiến trình) sẽ tự hiển thị trạng thái này
            }
            AuthResponse.Idle -> {
                // Trạng thái ban đầu hoặc đã reset, không làm gì đặc biệt
            }
        }
    }

    // Cấu trúc màn hình với TopAppBar và nội dung chính
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Phone Verification", fontWeight = FontWeight.SemiBold) }, // Tiêu đề của TopAppBar
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Nút quay lại
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues -> // paddingValues để xử lý padding từ TopAppBar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Áp dụng padding từ Scaffold
                .padding(horizontal = 24.dp, vertical = 16.dp), // Thêm padding cho nội dung
            horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
            verticalArrangement = Arrangement.Center // Căn giữa theo chiều dọc
        ) {
            Text(
                text = "Enter your phone number",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Trường nhập số điện thoại
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number (e.g., +84123456789)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), // Bàn phím tối ưu cho số điện thoại
                modifier = Modifier.fillMaxWidth(),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút "GET VERIFICATION CODE" (Lấy mã xác minh)
            Button(
                onClick = {
                    // Kiểm tra xem Activity có tồn tại không trước khi gọi ViewModel,
                    // vì Firebase Phone Auth cần Activity context.
                    if (activity != null) {
                        authViewModel.startPhoneAuth(activity, phoneNumber) // Gọi ViewModel để bắt đầu xác minh số điện thoại
                    } else {
                        Toast.makeText(context, "Activity not found for phone authentication. Cannot proceed.", Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            ) {
                if (authStatus is AuthResponse.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp)) // Hiển thị vòng tròn tiến trình khi đang tải
                } else {
                    Text("GET VERIFICATION CODE")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Enter verification code",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Trường nhập mã xác minh (OTP)
            OutlinedTextField(
                value = verificationCode,
                onValueChange = { verificationCode = it },
                label = { Text("Verification Code") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Bàn phím số
                modifier = Modifier.fillMaxWidth(),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút "VERIFY CODE" (Xác minh mã)
            Button(
                onClick = {
                    authViewModel.verifyPhoneNumberWithCode(verificationCode) // Gọi ViewModel để xác minh mã OTP
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            ) {
                if (authStatus is AuthResponse.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp)) // Hiển thị vòng tròn tiến trình khi đang tải
                } else {
                    Text("VERIFY CODE")
                }
            }
        }
    }
}