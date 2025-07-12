package com.example.btvn_nc_01

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHandler(
    permissions: List<String>,
    onAllPermissionsGranted: @Composable () -> Unit, // Nội dung hiển thị khi tất cả quyền được cấp
    // Map của quyền tới dữ liệu nội dung hộp thoại cụ thể của nó
    permissionDialogData: Map<String, PermissionDialogContentData>
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)
    val context = LocalContext.current

    // Trạng thái để theo dõi hộp thoại giải thích lý do của quyền nào đang hiển thị
    var currentPermissionToShowRationale by remember { mutableStateOf<String?>(null) }
    var showGoToSettingsDialog by remember { mutableStateOf(false) }

    // Effect để theo dõi trạng thái quyền và kích hoạt hộp thoại
    LaunchedEffect(multiplePermissionsState.permissions) {
        val pendingPermissions = multiplePermissionsState.permissions.filter { !it.status.isGranted }

        if (pendingPermissions.isNotEmpty()) {
            val nextPermission = pendingPermissions.first() // Xử lý từng quyền một

            if (nextPermission.status is PermissionStatus.Denied) {
                val deniedStatus = nextPermission.status as PermissionStatus.Denied
                if (deniedStatus.shouldShowRationale) {
                    currentPermissionToShowRationale = nextPermission.permission
                    showGoToSettingsDialog = false
                } else {
                    // Quyền bị từ chối vĩnh viễn (người dùng chọn "Don't ask again" hoặc từ chối 2 lần)
                    currentPermissionToShowRationale = null
                    showGoToSettingsDialog = true // Hiển thị hộp thoại "Đi tới cài đặt"
                }
            }
        } else {
            currentPermissionToShowRationale = null
            showGoToSettingsDialog = false
        }
    }

    when {
        multiplePermissionsState.allPermissionsGranted -> {
            onAllPermissionsGranted()
        }
        else -> {
            // Hiển thị hộp thoại giải thích lý do cho quyền hiện tại cần chú ý
            currentPermissionToShowRationale?.let { permission ->
                val data = permissionDialogData[permission]
                if (data != null) {
                    CustomPermissionDialog(
                        icon = data.icon,
                        title = data.title,
                        description = data.description,
                        onDismissRequest = {
                            currentPermissionToShowRationale = null
                            // Nếu bị từ chối, cân nhắc khởi chạy quyền tiếp theo hoặc hiển thị từ chối chung
                            // Để đơn giản, chúng ta chỉ đóng hộp thoại này.
                        },
                        onConfirm = {
                            currentPermissionToShowRationale = null // Đóng hộp thoại hiện tại
                            // Yêu cầu quyền cụ thể này hoặc tất cả các quyền còn lại
                            multiplePermissionsState.permissions.find { it.permission == permission }?.launchPermissionRequest()
                        },
                        confirmButtonText = data.confirmButtonText,
                        onSkip = {
                            currentPermissionToShowRationale = null // Đóng hộp thoại hiện tại
                            // Người dùng đã bỏ qua quyền này, tiến hành kiểm tra các quyền khác hoặc nội dung chính
                            // Logic này có thể được mở rộng để xử lý ý nghĩa của "bỏ qua" đối với ứng dụng của bạn
                        },
                        skipButtonText = data.skipButtonText
                    )
                }
            }

            // Hiển thị hộp thoại "Đi tới cài đặt" nếu bất kỳ quyền nào bị từ chối vĩnh viễn
            if (showGoToSettingsDialog && currentPermissionToShowRationale == null) { // Chỉ hiển thị khi không có hộp thoại rationale nào khác
                CustomPermissionDialog(
                    icon = Icons.Default.Info, // Biểu tượng chung cho cài đặt
                    title = "Yêu cầu quyền",
                    description = "Một số quyền cần thiết đã bị từ chối vĩnh viễn. Vui lòng cấp quyền thủ công trong Cài đặt ứng dụng.",
                    onDismissRequest = { showGoToSettingsDialog = false },
                    onConfirm = {
                        showGoToSettingsDialog = false
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        }
                        context.startActivity(intent)
                    },
                    confirmButtonText = "Đi đến Cài đặt",
                    onSkip = { showGoToSettingsDialog = false },
                    skipButtonText = "Bỏ qua"
                )
            }

            // Nội dung dự phòng khi không phải tất cả quyền đều được cấp và không có hộp thoại cụ thể nào hiển thị
            if (!multiplePermissionsState.allPermissionsGranted && currentPermissionToShowRationale == null && !showGoToSettingsDialog) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ứng dụng cần các quyền để hoạt động. Vui lòng chấp nhận các yêu cầu quyền.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = {
                        multiplePermissionsState.launchMultiplePermissionRequest() // Yêu cầu tất cả các quyền còn lại
                    }) {
                        Text("Yêu cầu lại quyền")
                    }
                }
            }
        }
    }
}

// Lớp dữ liệu để giữ nội dung cho mỗi hộp thoại quyền
data class PermissionDialogContentData(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val confirmButtonText: String,
    val skipButtonText: String
)