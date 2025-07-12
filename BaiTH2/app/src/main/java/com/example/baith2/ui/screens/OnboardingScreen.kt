package com.example.baith2.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baith2.R
import com.example.baith2.data.OnboardingPage

/**
 * Màn hình hiển thị từng trang trong luồng giới thiệu (Onboarding).
 * Gồm ảnh minh họa, tiêu đề, mô tả, các nút điều hướng và chỉ báo trang.
 *
 * @param onboardingPage Trang hiện tại đang hiển thị.
 * @param isLastPage Có phải là trang cuối cùng hay không.
 * @param currentPageIndex Chỉ số trang hiện tại (bắt đầu từ 0).
 * @param totalPages Tổng số trang trong luồng onboarding.
 * @param onNextClicked Callback khi nhấn nút Next / Get Started.
 * @param onBackClicked Callback khi nhấn nút Back.
 * @param onSkipClicked Callback khi nhấn nút Skip.
 */
@Composable
fun OnboardingScreen(
    onboardingPage: OnboardingPage,
    isLastPage: Boolean,
    currentPageIndex: Int,
    totalPages: Int,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onSkipClicked: () -> Unit
) {
    val primaryColor = MaterialTheme.colorScheme.primary

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // ==== TOP: Chỉ báo trang (dots) + nút Skip ====
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dots indicator
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(totalPages) { index ->
                        val dotColor = if (index == currentPageIndex) primaryColor else Color.LightGray
                        Canvas(modifier = Modifier.size(8.dp)) {
                            drawCircle(color = dotColor, radius = 8f)
                        }
                    }
                }

                // Skip button (có thể ẩn nếu là trang cuối)
                if (!isLastPage) {
                    TextButton(onClick = onSkipClicked) {
                        Text(text = "Skip")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ==== IMAGE MINH HỌA ====
            Image(
                painter = painterResource(id = onboardingPage.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(horizontal = 32.dp)
            )

            // ==== TIÊU ĐỀ & MÔ TẢ ====
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(id = onboardingPage.titleResId),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = onboardingPage.descriptionResId),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // ==== NÚT ĐIỀU HƯỚNG (Back / Next / Get Started) ====
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (onboardingPage != OnboardingPage.getOnboardingPages().first()) {
                    TextButton(onClick = onBackClicked) {
                        Text(text = "Back")
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }

                Button(
                    onClick = onNextClicked,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Text(text = if (isLastPage) "Get Started" else "Next")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    MaterialTheme {
        OnboardingScreen(
            onboardingPage = OnboardingPage.getOnboardingPages().first(),
            isLastPage = false,
            currentPageIndex = 0,
            totalPages = OnboardingPage.getOnboardingPages().size,
            onNextClicked = {},
            onBackClicked = {},
            onSkipClicked = {}
        )
    }
}
