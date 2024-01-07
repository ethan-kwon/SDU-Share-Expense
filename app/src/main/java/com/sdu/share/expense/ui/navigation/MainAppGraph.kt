package com.sdu.share.expense.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.screens.AddGroupScreen
import com.sdu.share.expense.ui.screens.GroupScreen
import com.sdu.share.expense.ui.screens.HomeScreen
import com.sdu.share.expense.ui.screens.ProfileScreen

fun NavGraphBuilder.mainAppGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
    groupViewModel: GroupViewModel
) {
    composable(route = ShareExpenseScreen.HOME_SCREEN.name) {
        HomeScreen(userViewModel,
            groupViewModel,
            onNavigateToAdd = {
                navController.navigate(ShareExpenseScreen.ADD_GROUP_SCREEN.name)
            },
            onNavigateToGroup = {
                navController.navigate(ShareExpenseScreen.GROUP_SCREEN.name)
            }
        )
    }
    composable(route = ShareExpenseScreen.PROFILE_SCREEN.name) {
        ProfileScreen()
    }
    composable(route = ShareExpenseScreen.ADD_GROUP_SCREEN.name) {
        AddGroupScreen(navController, userViewModel = userViewModel)
    }
    composable(route = ShareExpenseScreen.GROUP_SCREEN.name) {
        GroupScreen(navController, groupViewModel, userViewModel)
    }
}