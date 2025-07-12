package com.example.baith2.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.baith2.data.model.Product
import com.example.baith2.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductScreen() {
    // Khai báo trạng thái cho sản phẩm và trạng thái tải dữ liệu
    var product by remember { mutableStateOf<Product?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Gọi API khi màn hình được khởi tạo
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                product = RetrofitClient.api.getProduct() // Gọi API lấy sản phẩm
            } catch (e: Exception) {
                Log.e("API", "Lỗi gọi API", e) // Ghi log lỗi nếu có
            } finally {
                isLoading = false // Đặt trạng thái tải là hoàn thành
            }
        }
    }

    // Hiển thị vòng tròn tải nếu đang tải dữ liệu
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        // Hiển thị thông tin sản phẩm nếu có dữ liệu
        product?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Thẻ chứa hình ảnh sản phẩm
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(it.imgURL), // Tải ảnh từ URL
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách

                // Tên sản phẩm
                Text(
                    it.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 28.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách

                // Định dạng giá tiền (VND)
                val format = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
                val formattedPrice = format.format(it.price).replace("₫", "") + "₫"

                // Hiển thị giá
                Text(
                    "Giá: $formattedPrice",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )

                Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách

                // Thẻ chứa mô tả sản phẩm
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7))
                ) {
                    Text(
                        it.des,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 22.sp
                        )
                    )
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Không có dữ liệu.") // Hiển thị khi không có sản phẩm
        }
    }
}