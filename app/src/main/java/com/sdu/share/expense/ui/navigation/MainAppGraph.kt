package com.sdu.share.expense.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sdu.share.expense.ui.models.addgroup.AddGroupViewModel
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.group.RGroupViewModel
import com.sdu.share.expense.ui.models.profile.ProfileViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.screens.AddExpenseScreen
import com.sdu.share.expense.ui.screens.AddGroupScreen
import com.sdu.share.expense.ui.screens.ExpenseScreen
import com.sdu.share.expense.ui.screens.GroupScreen
import com.sdu.share.expense.ui.screens.HomeScreen
import com.sdu.share.expense.ui.screens.ProfileScreen

fun NavGraphBuilder.mainAppGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
    groupViewModel: GroupViewModel,
    rGroupViewModel: RGroupViewModel,
    profileViewModel: ProfileViewModel
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
        ProfileScreen(userViewModel, profileViewModel = profileViewModel)
    }
    composable(route = ShareExpenseScreen.ADD_GROUP_SCREEN.name) {
        AddGroupScreen(navController, userViewModel = userViewModel)
    }
    composable(route = ShareExpenseScreen.GROUP_SCREEN.name) {
        GroupScreen(navController, groupViewModel, onNavigateToAdd = {
            navController.navigate(ShareExpenseScreen.ADD_EXPENSE_SCREEN.name)
        },
            rgroupViewModel = rGroupViewModel,
            onNavigateToExpense = {
                navController.navigate(ShareExpenseScreen.EXPENSE_SCREEN.name)
            })
    }
    composable(route = ShareExpenseScreen.EXPENSE_SCREEN.name) {
        ExpenseScreen(rGroupViewModel = rGroupViewModel)
    }
    composable(route = ShareExpenseScreen.ADD_EXPENSE_SCREEN.name) {
        AddExpenseScreen(navController = navController, groupViewModel = groupViewModel,
            userViewModel = userViewModel)
    }
}