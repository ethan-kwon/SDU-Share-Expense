package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.PasswordTextField
import com.sdu.share.expense.ui.components.TwoNavigationButtonInRow
import com.sdu.share.expense.ui.components.UsernameTextField
import com.sdu.share.expense.ui.models.signin.SignInEvent
import com.sdu.share.expense.ui.models.signin.SignInViewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel,
    onCancelButtonClickedNavigateTo: () -> Unit,
    onSignInButtonClickedNavigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
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
        TwoNavigationButtonInRow(
            firstButtonLabel = R.string.cancel_button_label,
            secondButtonLabel = R.string.sign_in_button_label,
            onFirstButtonClicked = { onCancelButtonClickedNavigateTo() },
            onSecondButtonClicked = {
                signInViewModel.onEvent(
                    SignInEvent.SignInButtonHasBeenClicked(onSignInButtonClickedNavigateTo)
                )
            }
        )
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        signInViewModel = SignInViewModel(),
        onCancelButtonClickedNavigateTo = {},
        onSignInButtonClickedNavigateTo = {}
    )
}