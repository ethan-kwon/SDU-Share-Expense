package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.EditPasswordTextField
import com.sdu.share.expense.ui.components.EditTextField

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
        EditTextField(
            labelId = R.string.username_input_label,
            leadingIconId = R.drawable.account_circle_24px,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = username,
            onValueChange = { username = it }
        )
        EditPasswordTextField(
            labelId = R.string.password_input_label,
            leadingIconId = R.drawable.lock_24px,
            imeAction = ImeAction.Done,
            value = password,
            onValueChange = { password = it }
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