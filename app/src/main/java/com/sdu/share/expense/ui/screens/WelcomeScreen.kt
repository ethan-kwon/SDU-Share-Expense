package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sdu.share.expense.R

@Composable
fun WelcomeScreen(
    onSignInButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        OutlinedButton(
            onClick = onSignInButtonClick
        ) {
            Text(stringResource(R.string.sign_in_screen))
        }
        Button(
            onClick = onSignUpButtonClick
        ) {
            Text(stringResource(R.string.sign_up_screen))
        }
    }
}