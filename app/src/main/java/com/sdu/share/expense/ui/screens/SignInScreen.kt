package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sdu.share.expense.AppViewModelProvider
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.ErrorMessage
import com.sdu.share.expense.ui.components.MoneyBackgroundPreContentFrame
import com.sdu.share.expense.ui.components.PasswordTextField
import com.sdu.share.expense.ui.components.TwoNavigationButtonsParams
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.ui.components.UsernameTextField
import com.sdu.share.expense.ui.models.signin.SignInEvent
import com.sdu.share.expense.ui.models.signin.SignInViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel,
    userViewModel: UserViewModel,
    onCancelButtonClicked: () -> Unit,
    onSignInButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val headerText = UiText.StringResource(R.string.sign_in_screen_header)
    val subheaderText = UiText.StringResource(R.string.sign_in_screen_sub_header)

    val onCancelButtonResponse = {
        signInViewModel.onEvent(SignInEvent.CancelButtonHasBeenClicked(onCancelButtonClicked))
    }
    val onSignInButtonResponse = {
        signInViewModel.onEvent(
            SignInEvent.SignInButtonHasBeenClicked(
                coroutineScope,
                userViewModel,
                onSignInButtonClicked
            )
        )
    }
    val buttonsParams = TwoNavigationButtonsParams(
        R.string.cancel_button_label,
        R.string.sign_in_button_label,
        onCancelButtonResponse,
        onSignInButtonResponse
    )

    MoneyBackgroundPreContentFrame(
        headerTitle = headerText,
        subheaderTitle = subheaderText,
        buttonsParams = buttonsParams,
        modifier = modifier
    ) {
        UsernameTextField(
            errorMessage = signInViewModel.formState.usernameError,
            username = signInViewModel.formState.username,
            shouldErrorBeDisplayed = signInViewModel.shouldShowErrors,
            onUsernameChange = { signInViewModel.onEvent(SignInEvent.UsernameHasChanged(it)) },
            modifier = Modifier.padding(top = 4.dp)
        )
        PasswordTextField(
            labelId = R.string.password_input_label,
            errorMessage = signInViewModel.formState.passwordError,
            password = signInViewModel.formState.password,
            shouldErrorBeDisplayed = signInViewModel.shouldShowErrors,
            onPasswordChange = {
                signInViewModel.onEvent(SignInEvent.PasswordHasChanged(it))
            },
            modifier = Modifier.padding(top = 4.dp)
        )

        if (signInViewModel.shouldShowErrors
            && signInViewModel.formState.invalidCredentialsError != null
        ) {
            ErrorMessage(
                errorMessageContent = signInViewModel.formState.invalidCredentialsError!!,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        signInViewModel = viewModel(factory = AppViewModelProvider.Factory),
        userViewModel = viewModel(factory = AppViewModelProvider.Factory),
        onCancelButtonClicked = {},
        onSignInButtonClicked = {}
    )
}