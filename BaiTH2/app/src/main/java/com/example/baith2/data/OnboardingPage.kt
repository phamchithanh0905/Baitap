package com.example.baith2.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.baith2.R

/**
 * Data class đại diện cho một trang trong giao diện onboarding.
 * Mỗi trang bao gồm:
 * - Một hình minh họa (imageResId)
 * - Một tiêu đề (titleResId)
 * - Một mô tả ngắn gọn (descriptionResId)
 */
data class OnboardingPage(
    @DrawableRes val imageResId: Int,       // ID của ảnh minh họa
    @StringRes val titleResId: Int,         // ID của chuỗi tiêu đề
    @StringRes val descriptionResId: Int    // ID của chuỗi mô tả
) {
    companion object {
        /**
         * Trả về danh sách các trang onboarding đã được cấu hình sẵn.
         * Gồm 3 trang:
         * 1. Easy Time Management
         * 2. Increase Work Effectiveness
         * 3. Reminder Notification
         */
        fun getOnboardingPages(): List<OnboardingPage> {
            return listOf(
                OnboardingPage(
                    imageResId = R.drawable.img_onboarding_easy_time,
                    titleResId = R.string.onboarding_title_easy_time,
                    descriptionResId = R.string.onboarding_desc_easy_time
                ),
                OnboardingPage(
                    imageResId = R.drawable.img_onboarding_increase_work,
                    titleResId = R.string.onboarding_title_increase_work,
                    descriptionResId = R.string.onboarding_desc_increase_work
                ),
                OnboardingPage(
                    imageResId = R.drawable.img_onboarding_receive_notification,
                    titleResId = R.string.onboarding_title_receive_notification,
                    descriptionResId = R.string.onboarding_desc_receive_notification
                )
            )
        }
    }
}
