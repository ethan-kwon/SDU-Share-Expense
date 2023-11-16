package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sdu.share.expense.ui.models.SignUpViewModel
import com.sdu.share.expense.ui.navigation.ShareExpenseScreen
import com.sdu.share.expense.ui.navigation.components.ShareExpenseBottomBar
import com.sdu.share.expense.ui.navigation.components.ShareExpenseTopBar
import com.sdu.share.expense.ui.navigation.entryAppGraph
import com.sdu.share.expense.ui.navigation.mainAppGraph


@Composable
fun ShareExpenseApp(
    signUpViewModel: SignUpViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ShareExpenseScreen.valueOf(
        backStackEntry?.destination?.route ?: ShareExpenseScreen.WELCOME_SCREEN.name
    )
    val signUpUiState by signUpViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            if (shouldTopBarBeDisplayed(currentScreen)) {
                ShareExpenseTopBar(
                    currentScreen = currentScreen,
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
                signUpData = signUpUiState,
                setPersonalDetails = { firstName: String, lastName: String, email: String ->
                    signUpViewModel.setPersonalDetails(firstName, lastName, email)
                },
                setAccountDetails = { username: String,
                                      password: String,
                                      reTypedPassword: String,
                                      shouldSendNotifications: Boolean ->
                    signUpViewModel.setAccountDetails(
                        username, password, reTypedPassword, shouldSendNotifications
                    )
                },
                resetViewModel = {
                    signUpViewModel.resetSignUpData()
                }, saveAccount = {
                    /*TODO*/
                }
            )
            mainAppGraph(

            )
        }
    }
}

private fun shouldTopBarBeDisplayed(currentScreen: ShareExpenseScreen): Boolean {
    return currentScreen == ShareExpenseScreen.SIGN_UP_PERSONAL_DETAILS_SCREEN ||
            currentScreen == ShareExpenseScreen.SIGN_UP_ACCOUNT_DETAILS_SCREEN ||
            currentScreen == ShareExpenseScreen.SIGN_UP_SUMMARY_SCREEN
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
    } else {
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