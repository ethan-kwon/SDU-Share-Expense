package com.sdu.share.expense.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sdu.share.expense.ui.screens.HomeScreen
import com.sdu.share.expense.ui.screens.ProfileScreen

fun NavGraphBuilder.mainAppGraph() {
    composable(route = ShareExpenseScreen.HOME_SCREEN.name) {
        HomeScreen()
    }
    composable(route = ShareExpenseScreen.PROFILE_SCREEN.name) {
        ProfileScreen()
    }
}