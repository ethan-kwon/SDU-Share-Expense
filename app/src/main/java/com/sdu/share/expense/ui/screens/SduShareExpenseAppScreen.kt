package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sdu.share.expense.AppViewModelProvider
import com.sdu.share.expense.ui.models.addgroup.AddGroupViewModel
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.group.RGroupViewModel
import com.sdu.share.expense.ui.models.profile.ProfileViewModel
import com.sdu.share.expense.ui.models.signin.SignInViewModel
import com.sdu.share.expense.ui.models.signup.SignUpViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.navigation.ShareExpenseScreen
import com.sdu.share.expense.ui.navigation.components.ShareExpenseBottomBar
import com.sdu.share.expense.ui.navigation.components.ShareExpenseTopBar
import com.sdu.share.expense.ui.navigation.entryAppGraph
import com.sdu.share.expense.ui.navigation.mainAppGraph


@Composable
fun ShareExpenseApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ShareExpenseScreen.valueOf(
        backStackEntry?.destination?.route ?: ShareExpenseScreen.WELCOME_SCREEN.name
    )
    val signUpViewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val signInViewModel: SignInViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val groupViewModel: GroupViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val rGroupViewModel: RGroupViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val profileViewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)

    Scaffold(
        topBar = {
            if (shouldTopBarBeDisplayed(currentScreen)) {
                ShareExpenseTopBar(
                    title = currentScreen.name,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        }, bottomBar = {
            if (shouldBottomBarBeDisplayed(currentScreen)) {
                ShareExpenseBottomBar(
                    switchScreen = {
                        bottomBarSwitchScreen(navController, currentScreen)
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ShareExpenseScreen.WELCOME_SCREEN.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            entryAppGraph(
                navController = navController,
                signUpViewModel = signUpViewModel,
                signInViewModel = signInViewModel,
                userViewModel = userViewModel
            )
            mainAppGraph(
                userViewModel = userViewModel,
                navController = navController,
                groupViewModel = groupViewModel,
                rGroupViewModel = rGroupViewModel,
                profileViewModel = profileViewModel
            )
        }
    }
}

private fun shouldTopBarBeDisplayed(currentScreen: ShareExpenseScreen): Boolean {
    return false
}

private fun shouldBottomBarBeDisplayed(currentScreen: ShareExpenseScreen): Boolean {
    return currentScreen == ShareExpenseScreen.HOME_SCREEN ||
            currentScreen == ShareExpenseScreen.PROFILE_SCREEN
}

private fun bottomBarSwitchScreen(
    navController: NavHostController,
    currentScreen: ShareExpenseScreen
) {
    if (currentScreen == ShareExpenseScreen.HOME_SCREEN) {
        bottomBarNavigate(navController, ShareExpenseScreen.PROFILE_SCREEN.name)
    } else if (currentScreen == ShareExpenseScreen.PROFILE_SCREEN) {
        bottomBarNavigate(navController, ShareExpenseScreen.HOME_SCREEN.name)
    }
}

private fun bottomBarNavigate(navController: NavHostController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}