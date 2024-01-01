package com.sdu.share.expense.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.MoneyBackgroundPreContentFrame
import com.sdu.share.expense.ui.components.TwoNavigationButtonsParams
import com.sdu.share.expense.ui.components.UiText

@Composable
fun WelcomeScreen(
    onSignInButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val headerText = UiText.StringResource(R.string.welcome_to_app_header)
    val subheaderText = UiText.StringResource(R.string.welcome_to_app_sub_header)
    val buttonsParams = TwoNavigationButtonsParams(
        R.string.sign_in_screen,
        R.string.sign_up_screen,
        onSignInButtonClicked,
        onSignUpButtonClicked
    )

    MoneyBackgroundPreContentFrame(
        headerTitle = headerText,
        subheaderTitle = subheaderText,
        buttonsParams = buttonsParams,
        modifier = modifier
    ) {
        
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        onSignInButtonClicked = { },
        onSignUpButtonClicked = { }
    )
}