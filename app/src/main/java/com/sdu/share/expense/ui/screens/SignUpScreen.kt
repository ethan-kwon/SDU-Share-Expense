package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sdu.share.expense.AppViewModelProvider
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.GenericTextField
import com.sdu.share.expense.ui.components.PasswordTextField
import com.sdu.share.expense.ui.components.TwoNavigationButtonInRow
import com.sdu.share.expense.ui.components.UsernameTextField
import com.sdu.share.expense.ui.models.signup.SignUpEvent
import com.sdu.share.expense.ui.models.signup.SignUpViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PersonalDetailsScreen(
    signUpViewModel: SignUpViewModel,
    onCancelButtonClickedNavigateTo: () -> Unit,
    onNextButtonClickedNavigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        GenericTextField(
            labelId = R.string.first_name_input_label,
            leadingIconId = R.drawable.badge_24px,
            errorMessage = signUpViewModel.formState.firstNameError,
            value = signUpViewModel.formState.firstName,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowPersonalDetailsErrors,
            onValueChange = { signUpViewModel.onEvent(SignUpEvent.FirstNameHasChanged(it)) },
            isFinalField = false
        )
        GenericTextField(
            labelId = R.string.last_name_input_label,
            leadingIconId = R.drawable.badge_24px,
            errorMessage = signUpViewModel.formState.lastNameError,
            value = signUpViewModel.formState.lastName,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowPersonalDetailsErrors,
            onValueChange = { signUpViewModel.onEvent(SignUpEvent.LastNameHasChanged(it)) },
            isFinalField = false
        )
        GenericTextField(
            labelId = R.string.email_input_label,
            leadingIconId = R.drawable.badge_24px,
            errorMessage = signUpViewModel.formState.emailError,
            value = signUpViewModel.formState.email,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowPersonalDetailsErrors,
            onValueChange = { signUpViewModel.onEvent(SignUpEvent.EmailHasChanged(it)) },
            isFinalField = false
        )
        TwoNavigationButtonInRow(
            firstButtonLabel = R.string.cancel_button_label,
            secondButtonLabel = R.string.next_button_label,
            onFirstButtonClicked = onCancelButtonClickedNavigateTo,
            onSecondButtonClicked = {
                signUpViewModel.onEvent(
                    SignUpEvent.PersonalDataScreenNextButtonClicked(onNextButtonClickedNavigateTo)
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDetailsScreenPreview() {
    PersonalDetailsScreen(
        signUpViewModel = viewModel(factory = AppViewModelProvider.Factory),
        onCancelButtonClickedNavigateTo = {},
        onNextButtonClickedNavigateTo = {}
    )
}

@Composable
fun AccountDetailsScreen(
    signUpViewModel: SignUpViewModel,
    onCancelButtonClickedNavigateTo: () -> Unit,
    onNextButtonClickedNavigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        UsernameTextField(
            errorMessage = signUpViewModel.formState.usernameError,
            username = signUpViewModel.formState.username,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowAccountDetailsErrors,
            onUsernameChange = { signUpViewModel.onEvent(SignUpEvent.UsernameHasChanged(it)) }
        )
        PasswordTextField(
            labelId = R.string.new_password_input_label,
            errorMessage = signUpViewModel.formState.passwordError,
            password = signUpViewModel.formState.password,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowAccountDetailsErrors,
            onPasswordChange = { signUpViewModel.onEvent(SignUpEvent.PasswordHasChanged(it)) },
        )
        PasswordTextField(
            labelId = R.string.retype_password_input_label,
            errorMessage = signUpViewModel.formState.retypedPasswordError,
            password = signUpViewModel.formState.retypedPassword,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowAccountDetailsErrors,
            onPasswordChange = { signUpViewModel.onEvent(SignUpEvent.RetypedPasswordHasChanged(it)) },
        )
        Row {
            Text(stringResource(R.string.should_send_notifications_label))
            Checkbox(
                checked = signUpViewModel.formState.shouldSendNotification,
                onCheckedChange = {
                    signUpViewModel.onEvent(SignUpEvent.NotificationOptionHasChanged(it))
                }
            )
        }
        TwoNavigationButtonInRow(
            firstButtonLabel = R.string.cancel_button_label,
            secondButtonLabel = R.string.next_button_label,
            onFirstButtonClicked = onCancelButtonClickedNavigateTo,
            onSecondButtonClicked = {
                coroutineScope.launch(Dispatchers.IO) {
                    signUpViewModel.onEvent(
                        SignUpEvent.AccountDataScreenNextButtonClicked(onNextButtonClickedNavigateTo)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountDetailsScreenPreview() {
    AccountDetailsScreen(
        signUpViewModel = viewModel(factory = AppViewModelProvider.Factory),
        onCancelButtonClickedNavigateTo = {},
        onNextButtonClickedNavigateTo = {}
    )
}

@Composable
fun SignUpSummaryScreen(
    signUpViewModel: SignUpViewModel,
    onCancelButtonClickedNavigateTo: () -> Unit,
    onSaveAccountButtonClickedNavigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val items = listOf(
        Pair(
            stringResource(R.string.first_name_sign_up_summary_label),
            signUpViewModel.formState.firstName
        ),
        Pair(
            stringResource(R.string.last_name_sign_up_summary_label),
            signUpViewModel.formState.lastName
        ),
        Pair(
            stringResource(R.string.email_sign_up_summary_label),
            signUpViewModel.formState.email
        ),
        Pair(
            stringResource(R.string.username_sign_up_summary_label),
            signUpViewModel.formState.username
        ),
        Pair(
            stringResource(R.string.should_send_notifications_sign_up_summary_label),
            if (signUpViewModel.formState.shouldSendNotification) "true" else "false"
        )
    )

    Column(modifier = modifier) {
        items.forEach { item ->
            Text(item.first.uppercase())
            Text(text = item.second, fontWeight = FontWeight.Bold)
            Divider(thickness = 1.dp)
        }
        Spacer(modifier = Modifier)
        TwoNavigationButtonInRow(
            firstButtonLabel = R.string.cancel_button_label,
            secondButtonLabel = R.string.create_account_button_label,
            onFirstButtonClicked = onCancelButtonClickedNavigateTo,
            onSecondButtonClicked = {
                coroutineScope.launch {
                    signUpViewModel.saveUser()
                    onSaveAccountButtonClickedNavigateTo()
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpSummaryScreenPreview() {
    SignUpSummaryScreen(
        signUpViewModel = viewModel(factory = AppViewModelProvider.Factory),
        onCancelButtonClickedNavigateTo = { },
        onSaveAccountButtonClickedNavigateTo = { })
}
