package com.sdu.share.expense.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sdu.share.expense.AppViewModelProvider
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.GenericTextField
import com.sdu.share.expense.ui.components.MoneyBackgroundPreContentFrame
import com.sdu.share.expense.ui.components.PasswordTextField
import com.sdu.share.expense.ui.components.SignUpSummaryItem
import com.sdu.share.expense.ui.components.TextCheckbox
import com.sdu.share.expense.ui.components.TwoNavigationButtonsParams
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.ui.components.UsernameTextField
import com.sdu.share.expense.ui.models.signup.SignUpEvent
import com.sdu.share.expense.ui.models.signup.SignUpViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel

@Composable
fun PersonalDetailsScreen(
    signUpViewModel: SignUpViewModel,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val headerText = UiText.StringResource(R.string.sign_up_screens_header)
    val subheaderText = UiText.StringResource(R.string.sign_up_personal_details_screen_sub_header)

    val onCancelButtonClickedResponse = {
        signUpViewModel.onEvent(
            SignUpEvent.PersonalDetailsScreenCancelButtonClicked(onCancelButtonClicked)
        )
    }
    val onNextButtonClickedResponse = {
        signUpViewModel.onEvent(
            SignUpEvent.PersonalDetailsScreenNextButtonClicked(onNextButtonClicked)
        )
    }
    val buttonsParams = TwoNavigationButtonsParams(
        R.string.cancel_button_label,
        R.string.next_button_label,
        onCancelButtonClickedResponse,
        onNextButtonClickedResponse
    )

    MoneyBackgroundPreContentFrame(
        headerTitle = headerText,
        subheaderTitle = subheaderText,
        buttonsParams = buttonsParams,
        modifier = modifier
    ) {
        GenericTextField(
            labelId = R.string.first_name_input_label,
            leadingIconId = R.drawable.badge_24px,
            errorMessage = signUpViewModel.formState.firstNameError,
            value = signUpViewModel.formState.firstName,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowPersonalDetailsErrors,
            onValueChange = { signUpViewModel.onEvent(SignUpEvent.FirstNameHasChanged(it)) },
            isFinalField = false,
            modifier = Modifier.padding(top = 4.dp)
        )
        GenericTextField(
            labelId = R.string.last_name_input_label,
            leadingIconId = R.drawable.badge_24px,
            errorMessage = signUpViewModel.formState.lastNameError,
            value = signUpViewModel.formState.lastName,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowPersonalDetailsErrors,
            onValueChange = { signUpViewModel.onEvent(SignUpEvent.LastNameHasChanged(it)) },
            isFinalField = false,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        GenericTextField(
            labelId = R.string.email_input_label,
            leadingIconId = R.drawable.mail_24px,
            errorMessage = signUpViewModel.formState.emailError,
            value = signUpViewModel.formState.email,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowPersonalDetailsErrors,
            onValueChange = { signUpViewModel.onEvent(SignUpEvent.EmailHasChanged(it)) },
            isFinalField = true,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDetailsScreenPreview() {
    PersonalDetailsScreen(
        signUpViewModel = viewModel(factory = AppViewModelProvider.Factory),
        onCancelButtonClicked = {},
        onNextButtonClicked = {}
    )
}

@Composable
fun AccountDetailsScreen(
    signUpViewModel: SignUpViewModel,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val headerText = UiText.StringResource(R.string.sign_up_screens_header)
    val subheaderText = UiText.StringResource(R.string.sing_up_account_details_screen_sub_header)
    val buttonsParams = TwoNavigationButtonsParams(
        R.string.back_button_label,
        R.string.next_button_label,
        onBackButtonClicked,
    ) {
        signUpViewModel.onEvent(
            SignUpEvent.AccountDataScreenNextButtonClicked(
                coroutineScope, onNextButtonClicked
            )
        )
    }

    MoneyBackgroundPreContentFrame(
        headerTitle = headerText,
        subheaderTitle = subheaderText,
        buttonsParams = buttonsParams,
        modifier = modifier
    ) {
        UsernameTextField(
            errorMessage = signUpViewModel.formState.usernameError,
            username = signUpViewModel.formState.username,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowAccountDetailsErrors,
            onUsernameChange = { signUpViewModel.onEvent(SignUpEvent.UsernameHasChanged(it)) },
            modifier = Modifier.padding(top = 4.dp)
        )
        PasswordTextField(
            labelId = R.string.new_password_input_label,
            errorMessage = signUpViewModel.formState.passwordError,
            password = signUpViewModel.formState.password,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowAccountDetailsErrors,
            onPasswordChange = { signUpViewModel.onEvent(SignUpEvent.PasswordHasChanged(it)) },
            isFinalField = false,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        PasswordTextField(
            labelId = R.string.retype_password_input_label,
            errorMessage = signUpViewModel.formState.retypedPasswordError,
            password = signUpViewModel.formState.retypedPassword,
            shouldErrorBeDisplayed = signUpViewModel.shouldShowAccountDetailsErrors,
            onPasswordChange = { signUpViewModel.onEvent(SignUpEvent.RetypedPasswordHasChanged(it)) },
            modifier = Modifier.padding(bottom = 4.dp)
        )
        TextCheckbox(
            messageText = UiText.StringResource(R.string.should_send_notifications_label),
            isChecked = signUpViewModel.formState.shouldSendNotification,
            onValueChange = {
                signUpViewModel.onEvent(SignUpEvent.NotificationOptionHasChanged(it))
            },
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountDetailsScreenPreview() {
    AccountDetailsScreen(
        signUpViewModel = viewModel(factory = AppViewModelProvider.Factory),
        onBackButtonClicked = {},
        onNextButtonClicked = {}
    )
}


@Composable
fun SignUpSummaryScreen(
    signUpViewModel: SignUpViewModel,
    userViewModel: UserViewModel,
    onBackButtonClicked: () -> Unit,
    onSaveAccountButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val headerText = UiText.StringResource(R.string.sign_up_summary_screen_header)
    val buttonsParams = TwoNavigationButtonsParams(
        R.string.back_button_label,
        R.string.create_account_button_label,
        onBackButtonClicked,
    ) {
        signUpViewModel.onEvent(
            SignUpEvent.CreateAccountButtonClicked(
                coroutineScope,
                userViewModel,
                onSaveAccountButtonClicked,
            )
        )
    }
    val formState = signUpViewModel.formState
    val items = listOf(
        Pair(
            R.drawable.badge_24px,
            concatenateStrings(R.string.first_name_sign_up_summary_label, formState.firstName)
        ),
        Pair(
            R.drawable.badge_24px,
            concatenateStrings(R.string.last_name_sign_up_summary_label, formState.lastName)
        ),
        Pair(
            R.drawable.mail_24px,
            concatenateStrings(R.string.email_sign_up_summary_label, formState.email)
        ),
        Pair(
            R.drawable.account_circle_24px,
            concatenateStrings(R.string.username_sign_up_summary_label, formState.username)
        ),
        Pair(
            R.drawable.baseline_notifications_24,
            concatenateStrings(
                R.string.should_send_notifications_sign_up_summary_label,
                if (formState.shouldSendNotification) "Yes" else "No"
            )
        )
    )
    val lightGrayColor = colorResource(R.color.light_gray)

    MoneyBackgroundPreContentFrame(
        headerTitle = headerText,
        subheaderTitle = null,
        buttonsParams = buttonsParams,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
        ) {
            items.forEachIndexed { i, it ->
                SignUpSummaryItem(
                    it.first,
                    it.second,
                    modifier = Modifier.background(if (i % 2 == 1) lightGrayColor else Color.Transparent)
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
fun concatenateStrings(@StringRes stringId: Int, value: String): String {
    return stringResource(stringId) + ": " + value
}

@Preview(showBackground = true)
@Composable
fun SignUpSummaryScreenPreview() {
    SignUpSummaryScreen(
        signUpViewModel = viewModel(factory = AppViewModelProvider.Factory),
        userViewModel = viewModel(factory = AppViewModelProvider.Factory),
        onBackButtonClicked = { },
        onSaveAccountButtonClicked = { })
}
