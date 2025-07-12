package com.example.btvn_02.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

/**
 * AppTextField là một composable tái sử dụng dùng để hiển thị ô nhập liệu trong ứng dụng.
 *
 * @param value Giá trị hiện tại của ô nhập liệu.
 * @param onValueChange Hàm callback khi người dùng thay đổi nội dung.
 * @param label Nhãn hiển thị phía trên ô nhập.
 * @param modifier Modifier tùy chỉnh bố cục bên ngoài (mặc định là fillMaxWidth()).
 * @param keyboardOptions Tùy chọn bàn phím (mặc định: bàn phím hệ thống).
 * @param visualTransformation Hiệu ứng hiển thị văn bản (dùng để ẩn văn bản như mật khẩu).
 * @param maxLines Số dòng tối đa cho nội dung văn bản.
 * @param enabled Cho phép hoặc vô hiệu hóa ô nhập (true: bật, false: khóa).
 * @param textStyle Kiểu chữ được áp dụng cho nội dung nhập.
 * @param leadingIcon Icon hiển thị bên trái của ô nhập (tùy chọn, có thể null).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = 1,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        enabled = enabled,
        maxLines = maxLines,
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}
