package com.example.btvn_nc_01

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import compose.material.theme.ui.theme.Material3ComposeTheme // Đảm bảo import đúng

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val requiredPermissions = mutableListOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CAMERA
                    )
                    // Quyền POST_NOTIFICATIONS chỉ khả dụng từ Android 13 (API 33) trở lên
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        requiredPermissions.add(Manifest.permission.POST_NOTIFICATIONS)
                    }

                    val permissionDialogData = mapOf(
                        Manifest.permission.ACCESS_FINE_LOCATION to PermissionDialogContentData(
                            icon = Icons.Default.LocationOn,
                            title = "Dịch vụ Vị trí",
                            description = "Cho phép ứng dụng truy cập vị trí của bạn trong khi sử dụng ứng dụng để khám phá thế giới mà không bị lạc.",
                            confirmButtonText = "Cho phép",
                            skipButtonText = "Bỏ qua bây giờ"
                        ),
                        Manifest.permission.POST_NOTIFICATIONS to PermissionDialogContentData(
                            icon = Icons.Default.Notifications,
                            title = "Thông báo",
                            description = "Vui lòng bật thông báo để nhận cập nhật và lời nhắc.",
                            confirmButtonText = "Bật",
                            skipButtonText = "Bỏ qua bây giờ"
                        ),
                        Manifest.permission.CAMERA to PermissionDialogContentData(
                            icon = Icons.Default.CameraAlt,
                            title = "Máy ảnh",
                            description = "Chúng tôi cần quyền truy cập vào máy ảnh của bạn để quét mã QR.",
                            confirmButtonText = "Bật",
                            skipButtonText = "Bỏ qua bây giờ"
                        )
                    )

                    PermissionHandler(
                        permissions = requiredPermissions,
                        permissionDialogData = permissionDialogData,
                        onAllPermissionsGranted = {
                            AllPermissionsGrantedScreen()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AllPermissionsGrantedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "TẤT CẢ CÁC QUYỀN ĐÃ ĐƯỢC CẤP!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Bạn có thể sử dụng tất cả các chức năng của ứng dụng.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}
