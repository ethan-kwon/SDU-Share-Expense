package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.EditPasswordTextField
import com.sdu.share.expense.ui.components.PasswordTextField
import com.sdu.share.expense.ui.components.UsernameTextField

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier
) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column {
        UsernameTextField(
            username = username,
            onUsernameChange = { username = it }
        )
        PasswordTextField(
            password = password,
            onPasswordChange = { password = it }
        )
        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(R.string.sign_in_screen))
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}