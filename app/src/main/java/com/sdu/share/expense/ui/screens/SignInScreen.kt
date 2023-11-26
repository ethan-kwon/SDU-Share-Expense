package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sdu.share.expense.AppViewModelProvider
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.PasswordTextField
import com.sdu.share.expense.ui.components.TwoNavigationButtonInRow
import com.sdu.share.expense.ui.components.UsernameTextField
import com.sdu.share.expense.ui.models.signin.SignInEvent
import com.sdu.share.expense.ui.models.signin.SignInViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel,
    userViewModel: UserViewModel,
    onCancelButtonClickedNavigateTo: () -> Unit,
    onSignInButtonClickedNavigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        UsernameTextField(
            errorMessage = signInViewModel.formState.usernameError,
            username = signInViewModel.formState.username,
            shouldErrorBeDisplayed = signInViewModel.shouldShowErrors,
            onUsernameChange = { signInViewModel.onEvent(SignInEvent.UsernameHasChanged(it)) }
        )
        PasswordTextField(
            labelId = R.string.password_input_label,
            errorMessage = signInViewModel.formState.passwordError,
            password = signInViewModel.formState.password,
            shouldErrorBeDisplayed = signInViewModel.shouldShowErrors,
            onPasswordChange = {
                signInViewModel.onEvent(SignInEvent.PasswordHasChanged(it))
            }
        )

        if (signInViewModel.shouldShowErrors
            && signInViewModel.formState.invalidCredentialsError != null
        ) {
            Text(signInViewModel.formState.invalidCredentialsError!!.asString())
        }

        TwoNavigationButtonInRow(
            firstButtonLabel = R.string.cancel_button_label,
            secondButtonLabel = R.string.sign_in_button_label,
            onFirstButtonClicked = { onCancelButtonClickedNavigateTo() },
            onSecondButtonClicked = {
                signInViewModel.onEvent(
                    SignInEvent.SignInButtonHasBeenClicked(
                        coroutineScope,
                        userViewModel,
                        onSignInButtonClickedNavigateTo
                    )
                )
            }
        )
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        signInViewModel = viewModel(factory = AppViewModelProvider.Factory),
        userViewModel = viewModel(factory = AppViewModelProvider.Factory),
        onCancelButtonClickedNavigateTo = {},
        onSignInButtonClickedNavigateTo = {}
    )
}