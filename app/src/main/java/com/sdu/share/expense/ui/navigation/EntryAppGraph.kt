package com.sdu.share.expense.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sdu.share.expense.ui.models.signin.SignInViewModel
import com.sdu.share.expense.ui.models.signup.SignUpViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.screens.AccountDetailsScreen
import com.sdu.share.expense.ui.screens.PersonalDetailsScreen
import com.sdu.share.expense.ui.screens.SignInScreen
import com.sdu.share.expense.ui.screens.SignUpSummaryScreen
import com.sdu.share.expense.ui.screens.WelcomeScreen


fun NavGraphBuilder.entryAppGraph(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel,
    signInViewModel: SignInViewModel,
    userViewModel: UserViewModel,
) {
    composable(route = ShareExpenseScreen.WELCOME_SCREEN.name) {
        WelcomeScreen(
            onSignInButtonClick = {
                navController.navigate(ShareExpenseScreen.SIGN_IN_SCREEN.name)
            },
            onSignUpButtonClick = {
                navController.navigate(ShareExpenseScreen.SIGN_UP_PERSONAL_DETAILS_SCREEN.name)
            }
        )
    }
    composable(route = ShareExpenseScreen.SIGN_IN_SCREEN.name) {
        SignInScreen(
            signInViewModel = signInViewModel,
            userViewModel = userViewModel,
            onCancelButtonClickedNavigateTo = {
                navigateToWelcomeScreen(navController)
            },
            onSignInButtonClickedNavigateTo = {
                navController.navigate(ShareExpenseScreen.HOME_SCREEN.name)
            }
        )
    }
    composable(route = ShareExpenseScreen.SIGN_UP_PERSONAL_DETAILS_SCREEN.name) {
        PersonalDetailsScreen(
            signUpViewModel = signUpViewModel,
            onCancelButtonClickedNavigateTo = {
                navigateToWelcomeScreen(navController)
            },
            onNextButtonClickedNavigateTo = {
                navController.navigate(ShareExpenseScreen.SIGN_UP_ACCOUNT_DETAILS_SCREEN.name)
            })
    }
    composable(route = ShareExpenseScreen.SIGN_UP_ACCOUNT_DETAILS_SCREEN.name) {
        AccountDetailsScreen(
            signUpViewModel = signUpViewModel,
            onCancelButtonClickedNavigateTo = {
                navigateToWelcomeScreen(navController)
            },
            onNextButtonClickedNavigateTo = {
                navController.navigate(ShareExpenseScreen.SIGN_UP_SUMMARY_SCREEN.name)
            })
    }
    composable(route = ShareExpenseScreen.SIGN_UP_SUMMARY_SCREEN.name) {
        SignUpSummaryScreen(
            signUpViewModel = signUpViewModel,
            onSaveAccountButtonClickedNavigateTo = {
                navController.navigate(ShareExpenseScreen.HOME_SCREEN.name)
            },
            onCancelButtonClickedNavigateTo = {
                navigateToWelcomeScreen(navController)
            }
        )
    }
}

private fun navigateToWelcomeScreen(navController: NavHostController) {
    navController.popBackStack(ShareExpenseScreen.WELCOME_SCREEN.name, inclusive = false)
}
