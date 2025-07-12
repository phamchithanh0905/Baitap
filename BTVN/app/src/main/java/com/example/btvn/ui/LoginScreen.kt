package com.example.btvn.ui

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.btvn.R
import com.example.btvn.model.AuthResponse
import com.example.btvn.navigation.Screen
import com.example.btvn.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

/**
 * Màn hình đăng nhập của ứng dụng. Hỗ trợ đăng nhập bằng Email/Mật khẩu, Google và chuyển hướng đến màn hình xác thực Số điện thoại.
 *
 * @param navController Bộ điều khiển điều hướng để chuyển đổi màn hình.
 * @param authViewModel ViewModel chứa logic xác thực, được Hilt cung cấp tự động.
 */
@OptIn(ExperimentalMaterial3Api::class) // Cần để sử dụng OutlinedTextField
@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel() // Khởi tạo ViewModel
) {
    val context = LocalContext.current // Lấy context hiện tại
    val authStatus by authViewModel.authStatus.collectAsState() // Lắng nghe trạng thái xác thực từ ViewModel

    // Trạng thái cho các trường nhập liệu Email và Mật khẩu
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // Trạng thái ẩn/hiện mật khẩu

    // Cấu hình Google Sign-In Options (GSO) để tích hợp với Firebase
    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id)) // Yêu cầu ID Token từ Google
            .requestEmail() // Yêu cầu quyền truy cập email
            .build()
    }

    // Lấy GoogleSignInClient để thực hiện đăng nhập Google
    val googleSignInClient: GoogleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

    // Launcher để xử lý kết quả trả về từ Intent đăng nhập Google
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult() // Hợp đồng cho kết quả Activity
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) { // Nếu hoạt động thành công
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account.idToken
                if (idToken != null) {
                    authViewModel.signInWithGoogle(idToken) // Gọi ViewModel để đăng nhập Firebase với Google Token
                } else {
                    Toast.makeText(context, "Google ID Token is null.", Toast.LENGTH_LONG).show()
                }
            } catch (e: ApiException) {
                // Xử lý lỗi từ Google Sign-In API
                Toast.makeText(context, "Google Sign-In failed: ${e.statusCode} - ${e.message}", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Google Sign-In cancelled.", Toast.LENGTH_SHORT).show()
        }
    }

    // Theo dõi trạng thái xác thực để điều hướng hoặc hiển thị thông báo
    LaunchedEffect(authStatus) {
        when (val status = authStatus) {
            is AuthResponse.Success -> {
                // Đăng nhập thành công, điều hướng đến ProfileScreen
                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.ProfileScreen.route) {
                    // Xóa toàn bộ back stack để người dùng không thể quay lại màn hình đăng nhập
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
                authViewModel.resetAuthStatus() // Đặt lại trạng thái để tránh điều hướng lại khi recompose
            }
            is AuthResponse.Error -> {
                // Đăng nhập thất bại, hiển thị thông báo lỗi
                Toast.makeText(context, "Login failed: ${status.errorMessage}", Toast.LENGTH_LONG).show()
                authViewModel.resetAuthStatus() // Đặt lại trạng thái để chỉ hiển thị lỗi một lần
            }
            AuthResponse.Loading -> {
                // Đang tải: UI (nút, thanh tiến trình) sẽ tự động cập nhật trạng thái "enabled/disabled"
            }
            AuthResponse.Idle -> {
                // Trạng thái ban đầu hoặc đã reset, không làm gì đặc biệt
            }
        }
    }

    // Giao diện người dùng của màn hình đăng nhập
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo ứng dụng
            Image(
                painter = painterResource(id = R.drawable.uth_logo), // Đảm bảo đã thêm uth_logo.png vào thư mục drawable
                contentDescription = "UTH Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Tiêu đề và mô tả ứng dụng
            Text(
                text = "SmartTasks",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "A simple and efficient to-do app",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Lời chào mừng
            Text(
                text = "Welcome",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Ready to explore? Log in to get started.",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Trường nhập Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), // Bàn phím tối ưu cho email
                leadingIcon = { Icon(painterResource(id = R.drawable.ic_email), contentDescription = "Email Icon") }, // Đảm bảo ic_email.xml tồn tại trong drawable
                modifier = Modifier.fillMaxWidth(),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Trường nhập Mật khẩu
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Bàn phím tối ưu cho mật khẩu
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), // Ẩn/hiện mật khẩu
                leadingIcon = { Icon(painterResource(id = R.drawable.ic_lock), contentDescription = "Lock Icon") }, // Đảm bảo ic_lock.xml tồn tại trong drawable
                trailingIcon = { // Nút ẩn/hiện mật khẩu
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility // Icon hiển thị
                    else Icons.Filled.VisibilityOff // Icon ẩn
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút ĐĂNG NHẬP BẰNG EMAIL/MẬT KHẨU
            Button(
                onClick = {
                    // THÊM KIỂM TRA ĐẦU VÀO TẠI ĐÂY cho ĐĂNG NHẬP
                    if (email.isBlank() || password.isBlank()) { // isBlank() kiểm tra cả rỗng và chỉ có khoảng trắng
                        Toast.makeText(context, "Email and Password cannot be empty for login.", Toast.LENGTH_SHORT).show()
                    } else {
                        authViewModel.signInWithEmailPassword(email, password) // Gọi ViewModel để đăng nhập
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            ) {
                if (authStatus is AuthResponse.Loading) {
                    CircularProgressIndicator( // Hiển thị vòng tròn tiến trình khi đang tải
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("SIGN IN WITH EMAIL/PASSWORD")
                }
            }

            Spacer(modifier = Modifier.height(8.dp)) // Giảm khoảng cách để thêm nút đăng ký

            // Văn bản "Don't have an account?"
            Text(
                text = "Don't have an account?",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Nút ĐĂNG KÝ BẰNG EMAIL/MẬT KHẨU
            TextButton(
                onClick = {
                    // THÊM KIỂM TRA ĐẦU VÀO TẠI ĐÂY cho ĐĂNG KÝ
                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Email and Password cannot be empty for registration.", Toast.LENGTH_SHORT).show()
                    } else {
                        authViewModel.registerWithEmailPassword(email, password) // Gọi ViewModel để đăng ký
                    }
                },
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            ) {
                if (authStatus is AuthResponse.Loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp) // Kích thước nhỏ hơn cho TextButton
                    )
                } else {
                    Text("REGISTER WITH EMAIL/PASSWORD")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Text "OR"
            Text(
                text = "OR",
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Nút ĐĂNG NHẬP BẰNG GOOGLE
            Button(
                onClick = {
                    // Đăng xuất khỏi phiên Google trước đó để tránh lỗi nếu người dùng muốn đổi tài khoản
                    googleSignInClient.signOut().addOnCompleteListener {
                        googleSignInLauncher.launch(googleSignInClient.signInIntent) // Khởi chạy Intent đăng nhập Google
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            ) {
                if (authStatus is AuthResponse.Loading) {
                    CircularProgressIndicator( // Hiển thị vòng tròn tiến trình khi đang tải
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google), // Đảm bảo đã thêm ic_google.xml hoặc .png vào drawable
                        contentDescription = "Google Sign-In",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("SIGN IN WITH GOOGLE")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nút ĐĂNG NHẬP BẰNG SỐ ĐIỆN THOẠI (chuyển hướng)
            Button(
                onClick = {
                    navController.navigate(Screen.PhoneAuthScreen.route) // Điều hướng đến màn hình xác thực số điện thoại
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                enabled = authStatus !is AuthResponse.Loading // Vô hiệu hóa khi đang tải
            ) {
                if (authStatus is AuthResponse.Loading) {
                    CircularProgressIndicator( // Hiển thị vòng tròn tiến trình khi đang tải
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_phone), // Đảm bảo đã thêm ic_phone.xml hoặc .png vào drawable
                        contentDescription = "Phone Sign-In",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("SIGN IN WITH PHONE")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Chú thích bản quyền
            Text(
                text = "© UTHSmartTasks",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}