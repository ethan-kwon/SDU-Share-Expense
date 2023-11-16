package com.sdu.share.expense.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sdu.share.expense.models.SignUpData
import com.sdu.share.expense.ui.screens.AccountDetailsScreen
import com.sdu.share.expense.ui.screens.PersonalDetailsScreen
import com.sdu.share.expense.ui.screens.SignInScreen
import com.sdu.share.expense.ui.screens.SignUpSummaryScreen
import com.sdu.share.expense.ui.screens.WelcomeScreen


fun NavGraphBuilder.entryAppGraph(
    navController: NavHostController,
    signUpData: SignUpData,
    setPersonalDetails: (firstName: String, lastName: String, email: String) -> Unit,
    setAccountDetails: (username: String, password: String, reTypedPassword: String) -> Unit,
    resetViewModel: () -> Unit
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

        )
    }
    composable(route = ShareExpenseScreen.SIGN_UP_PERSONAL_DETAILS_SCREEN.name) {
        PersonalDetailsScreen(
            onCancelButtonClicked = {
                cancelSigningUpAndNavigateToWelcomeScreen(resetViewModel, navController)
            },
            onNextButtonClicked = { firstName, lastName, email ->
                setPersonalDetails(firstName, lastName, email)
                navController.navigate(ShareExpenseScreen.SIGN_UP_ACCOUNT_DETAILS_SCREEN.name)
            })
    }
    composable(route = ShareExpenseScreen.SIGN_UP_ACCOUNT_DETAILS_SCREEN.name) {
        AccountDetailsScreen(
            onCancelButtonClicked = {
                cancelSigningUpAndNavigateToWelcomeScreen(resetViewModel, navController)
            },
            onNextButtonClicked = { username, password, reTypedPassword ->
                setAccountDetails(username, password, reTypedPassword)
                navController.navigate(ShareExpenseScreen.SIGN_UP_SUMMARY_SCREEN.name)
            })
    }
    composable(route = ShareExpenseScreen.SIGN_UP_SUMMARY_SCREEN.name) {
        SignUpSummaryScreen(
            signUpData = signUpData
        )
    }
}

private fun cancelSigningUpAndNavigateToWelcomeScreen(
    resetViewModel: () -> Unit,
    navController: NavHostController
) {
    resetViewModel()
    navController.popBackStack(ShareExpenseScreen.WELCOME_SCREEN.name, inclusive = false)
}
