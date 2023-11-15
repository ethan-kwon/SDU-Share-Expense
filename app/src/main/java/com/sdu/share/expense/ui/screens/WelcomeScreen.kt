package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
            Text("Sign in")
        }
        Button(
            onClick = onSignUpButtonClick
        ) {
            Text("Sign up")
        }
    }
}