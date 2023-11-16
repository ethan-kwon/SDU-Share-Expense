package com.sdu.share.expense.ui.screens

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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sdu.share.expense.ui.models.SignUpViewModel
import com.sdu.share.expense.ui.navigation.ShareExpenseScreen
import com.sdu.share.expense.ui.navigation.entryAppGraph


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
            entryAppGraph(
                navController = navController,
                signUpData = signUpUiState,
                setPersonalDetails = { firstName: String, lastName: String, email: String ->
                    signUpViewModel.setPersonalDetails(firstName, lastName, email)
                },
                setAccountDetails = { username: String, password: String, reTypedPassword: String ->
                    signUpViewModel.setAccountDetails(
                        username,
                        password,
                        reTypedPassword, /*TODO*/
                        false
                    )
                },
                resetViewModel = {
                    signUpViewModel.resetSignUpData()
                }
            )
        }
    }
}