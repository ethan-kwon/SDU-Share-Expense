package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdu.share.expense.R
import com.sdu.share.expense.models.SignUpData
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
    onNextButtonClicked: (firstName: String, lastName: String, email: String) -> Unit,
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
            onSecondButtonClicked = { onNextButtonClicked(firstName, lastName, email) }
        )
    }
}

@Preview
@Composable
fun PersonalDetailsScreenPreview() {
    PersonalDetailsScreen(
        onCancelButtonClicked = {},
        onNextButtonClicked = { _, _, _ -> }
    )
}

@Composable
fun AccountDetailsScreen(
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: (username: String, password: String, reTypedPassword: String) -> Unit,
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
            onSecondButtonClicked = { onNextButtonClicked(username, password, reTypedPassword) }
        )
    }
}

@Preview
@Composable
fun AccountDetailsScreenPreview() {
    AccountDetailsScreen(
        onCancelButtonClicked = {},
        onNextButtonClicked = { _, _, _ -> }
    )
}

@Composable
fun SignUpSummaryScreen(
    signUpData: SignUpData,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Pair(stringResource(R.string.first_name_sign_up_summary_label), signUpData.firstName),
        Pair(stringResource(R.string.last_name_sign_up_summary_label), signUpData.lastName),
        Pair(stringResource(R.string.email_sign_up_summary_label), signUpData.email),
        Pair(stringResource(R.string.username_sign_up_summary_label), signUpData.username),
        Pair(
            stringResource(R.string.should_send_notifications_sign_up_summary_label),
            if (signUpData.shouldSendNotification) "true" else "false"
        )
    )

    Column(modifier = modifier) {
        items.forEach { item ->
            Text(item.first.uppercase())
            Text(text = item.second, fontWeight = FontWeight.Bold)
            Divider(thickness = 1.dp)
        }
    }
}