package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.GenericTextField
import com.sdu.share.expense.ui.components.PasswordTextField
import com.sdu.share.expense.ui.components.TwoNavigationButtonInRow
import com.sdu.share.expense.ui.components.UsernameTextField

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Text("Sign up screen")
}

@Composable
fun PersonalDetailsScreen(
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var firstName by remember {
        mutableStateOf("");
    }

    var lastName by remember {
        mutableStateOf("");
    }

    var email by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        GenericTextField(
            labelId = R.string.first_name_input_label,
            leadingIconId = R.drawable.badge_24px,
            value = firstName,
            onValueChange = { firstName = it },
            isFinalField = false
        )
        GenericTextField(
            labelId = R.string.last_name_input_label,
            leadingIconId = R.drawable.badge_24px,
            value = lastName,
            onValueChange = { lastName = it },
            isFinalField = false
        )
        GenericTextField(
            labelId = R.string.email_input_label,
            leadingIconId = R.drawable.badge_24px,
            value = email,
            onValueChange = { email = it },
            isFinalField = false
        )
        TwoNavigationButtonInRow(
            firstButtonLabel = R.string.cancel_button_label,
            secondButtonLabel = R.string.next_button_label,
            onFirstButtonClicked = onCancelButtonClicked,
            onSecondButtonClicked = onNextButtonClicked
        )
    }
}

@Preview
@Composable
fun PersonalDetailsScreenPreview() {
    PersonalDetailsScreen(
        onCancelButtonClicked = {},
        onNextButtonClicked = {}
    )
}

@Composable
fun AccountDetailsScreen(
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember {
        mutableStateOf("");
    }

    var password by remember {
        mutableStateOf("");
    }

    var reTypedPassword by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        UsernameTextField(
            username = username,
            onUsernameChange = { username = it; }
        )
        PasswordTextField(
            password = password,
            onPasswordChange = { password = it; },
            labelId = R.string.new_password_input_label
        )
        PasswordTextField(
            password = reTypedPassword,
            onPasswordChange = { reTypedPassword = it; },
            labelId = R.string.retype_password_input_label
        )
        TwoNavigationButtonInRow(
            firstButtonLabel = R.string.cancel_button_label,
            secondButtonLabel = R.string.create_account_button_label,
            onFirstButtonClicked = onCancelButtonClicked,
            onSecondButtonClicked = onNextButtonClicked
        )
    }
}

@Preview
@Composable
fun AccountDetailsScreenPreview() {
    AccountDetailsScreen(
        onCancelButtonClicked = {},
        onNextButtonClicked = {}
    )
}