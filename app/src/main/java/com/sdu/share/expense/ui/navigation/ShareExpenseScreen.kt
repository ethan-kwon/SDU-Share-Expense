package com.sdu.share.expense.ui.navigation

import androidx.annotation.StringRes
import com.sdu.share.expense.R

enum class ShareExpenseScreen(@StringRes val title: Int) {
    WELCOME_SCREEN(R.string.welcome_screen),
    SIGN_IN_SCREEN(R.string.sign_in_screen),
    SIGN_UP_PERSONAL_DETAILS_SCREEN(R.string.sign_up_personal_details_screen),
    SIGN_UP_ACCOUNT_DETAILS_SCREEN(R.string.sign_up_account_details_screen),
    SIGN_UP_SUMMARY_SCREEN(R.string.sign_up_summary_screen),
    HOME_SCREEN(R.string.home_screen),
    PROFILE_SCREEN(R.string.profile_screen)
}