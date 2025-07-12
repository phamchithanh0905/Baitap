package com.example.btvn_nc_01

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.ButtonDefaults // Thêm import này

@Composable
fun CustomPermissionDialog(
    icon: ImageVector, // Biểu tượng cho quyền (ví dụ: Icons.Default.LocationOn)
    title: String,
    description: String,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    confirmButtonText: String,
    onSkip: () -> Unit,
    skipButtonText: String
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = false, // Không đóng khi nhấn nút back
            dismissOnClickOutside = false // Không đóng khi nhấn ra ngoài
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface) // Sử dụng màu surface của theme
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(72.dp),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary) // Sử dụng màu primary của theme
            ) {
                Text(confirmButtonText, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = onSkip,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary) // Nút bỏ qua có màu viền
            ) {
                Text(skipButtonText, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}