package com.sdu.share.expense.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.models.SignUpViewModel

enum class ShareExpenseScreen(@StringRes val title: Int) {
    WELCOME_SCREEN(R.string.welcome_screen),
    SIGN_IN_SCREEN(R.string.sign_in_screen),
    SIGN_UP_PERSONAL_DETAILS_SCREEN(R.string.sign_up_personal_details_screen),
    SIGN_UP_ACCOUNT_DETAILS_SCREEN(R.string.sign_up_account_details_screen),
    SIGN_UP_SUMMARY_SCREEN(R.string.sign_up_summary_screen)
}

@Composable
fun ShareExpenseAppTopBar(
    currentScreen: ShareExpenseScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            }
        }
    )
}

@Composable
fun ShareExpenseApp(
    viewModel: SignUpViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ShareExpenseScreen.valueOf(
        backStackEntry?.destination?.route ?: ShareExpenseScreen.WELCOME_SCREEN.name
    )
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            ShareExpenseAppTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ShareExpenseScreen.WELCOME_SCREEN.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = ShareExpenseScreen.WELCOME_SCREEN.name) {
                WelcomeScreen(
                    onSignInButtonClick = { navController.navigate(ShareExpenseScreen.SIGN_IN_SCREEN.name) },
                    onSignUpButtonClick = { navController.navigate(ShareExpenseScreen.SIGN_UP_PERSONAL_DETAILS_SCREEN.name) }
                )
            }
            composable(route = ShareExpenseScreen.SIGN_IN_SCREEN.name) {
                SignInScreen(

                )
            }
            composable(route = ShareExpenseScreen.SIGN_UP_PERSONAL_DETAILS_SCREEN.name) {
                PersonalDetailsScreen(
                    onCancelButtonClicked = { /*TODO*/ },
                    onNextButtonClicked = { firstName, lastName, email ->
                        viewModel.setPersonalDetails(firstName, lastName, email)
                        navController.navigate(ShareExpenseScreen.SIGN_UP_ACCOUNT_DETAILS_SCREEN.name)
                    })
            }
            composable(route = ShareExpenseScreen.SIGN_UP_ACCOUNT_DETAILS_SCREEN.name) {
                AccountDetailsScreen(
                    onCancelButtonClicked = { /*TODO*/ },
                    onNextButtonClicked = { username, password, reTypedPassword ->
                        viewModel.setAccountDetails(username, password, reTypedPassword, false)
                        navController.navigate(ShareExpenseScreen.SIGN_UP_SUMMARY_SCREEN.name)
                    })
            }
            composable(route = ShareExpenseScreen.SIGN_UP_SUMMARY_SCREEN.name) {
                SignUpSummaryScreen(
                    signUpData = uiState
                )
            }
        }
    }
}