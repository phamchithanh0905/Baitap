package com.example.btvn_02.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable nút chính được sử dụng xuyên suốt trong ứng dụng.
 * Đây là thành phần tái sử dụng giúp đảm bảo tính nhất quán về giao diện và hành vi.
 *
 * @param text Chuỗi văn bản hiển thị trên nút.
 * @param onClick Hàm callback được gọi khi nút được nhấn.
 * @param modifier Cho phép tuỳ biến bố cục bên ngoài (mặc định: Modifier rỗng).
 * @param enabled Trạng thái hoạt động của nút (true: bật, false: vô hiệu hoá).
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp), // Chiều cao tiêu chuẩn
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium // Sử dụng typography chuẩn
        )
    }
}
