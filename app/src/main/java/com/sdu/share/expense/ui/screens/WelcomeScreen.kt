package com.sdu.share.expense.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.TwoNavigationButtonInRow

@Composable
fun WelcomeScreen(
    onSignInButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TwoNavigationButtonInRow(
        firstButtonLabel = R.string.sign_in_screen,
        secondButtonLabel = R.string.sign_up_screen,
        onFirstButtonClicked = onSignInButtonClick,
        onSecondButtonClicked = onSignUpButtonClick
    )
}