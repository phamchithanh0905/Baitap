package com.example.btvn_02.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Bảng màu sáng (hiện tại chỉ dùng chế độ Light Theme cho bản demo)
private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,     // Màu chính (nút, icon...)
    onPrimary = White,         // Màu chữ trên nền chính
    surface = White,           // Màu nền của các thành phần giao diện
    onSurface = TextColor,     // Màu chữ trên bề mặt
    background = White,        // Màu nền toàn cục
    onBackground = TextColor   // Màu chữ trên nền chính
)

@Composable
fun BTVN_02Theme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Có thể mở rộng để hỗ trợ chế độ tối
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme // Chỉ sử dụng chế độ sáng

    // Thiết lập màu cho thanh trạng thái
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = darkTheme // Light icons nếu darkTheme = false
        }
    }

    // Áp dụng MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
