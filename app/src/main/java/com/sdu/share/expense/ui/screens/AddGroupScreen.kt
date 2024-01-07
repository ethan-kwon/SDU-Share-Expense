package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.navigation.components.ShareExpenseTopBar

@Composable
fun AddGroupScreen(
    navController: NavHostController,
    userViewModel: UserViewModel) {
    Scaffold(
        topBar = {
            ShareExpenseTopBar(
                title = "Add New Group",
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }

}