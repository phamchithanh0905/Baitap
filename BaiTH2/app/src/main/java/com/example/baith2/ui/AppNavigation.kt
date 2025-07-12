package com.example.baith2.ui

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baith2.data.OnboardingPage
import com.example.baith2.navigation.AppScreen
import com.example.baith2.ui.screens.OnboardingScreen
import com.example.baith2.ui.screens.SplashScreen

/**
 * Hàm quản lý điều hướng chính cho toàn bộ ứng dụng.
 *
 * @param navController NavController điều hướng giữa các màn hình.
 */
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Splash.route
    ) {

        // ========== Màn hình Splash ==========
        composable(AppScreen.Splash.route) {
            SplashScreen(navController = navController)
        }

        // ========== Luồng Onboarding ==========
        composable(AppScreen.Onboarding.route) {
            val onboardingPages = remember { OnboardingPage.getOnboardingPages() }
            var currentPageIndex by remember { mutableIntStateOf(0) }

            val currentPage = onboardingPages[currentPageIndex]
            val isLastPage = currentPageIndex == onboardingPages.lastIndex

            OnboardingScreen(
                onboardingPage = currentPage,
                isLastPage = isLastPage,
                currentPageIndex = currentPageIndex,
                totalPages = onboardingPages.size,
                onNextClicked = {
                    if (!isLastPage) {
                        currentPageIndex++
                    } else {
                        currentPageIndex = 0 // Reset nếu cần xem lại từ đầu
                    }
                },
                onBackClicked = {
                    if (currentPageIndex > 0) {
                        currentPageIndex--
                    }
                },
                onSkipClicked = {
                    if (!isLastPage) {
                        currentPageIndex++
                    }
                }
            )
        }
    }
}
