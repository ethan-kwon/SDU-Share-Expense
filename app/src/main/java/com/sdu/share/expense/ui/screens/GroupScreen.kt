package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.navigation.ShareExpenseScreen
import com.sdu.share.expense.ui.navigation.components.ShareExpenseTopBar

@Composable
fun GroupScreen(
    navController: NavHostController,
    groupViewModel: GroupViewModel,
    userViewModel: UserViewModel
) {
    val group = groupViewModel.state.group
    
    Column {
        ShareExpenseTopBar(
            title = group.name,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() })
    }
}