package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.TwoNavigationButtonInRow

@Composable
fun WelcomeScreen(
    onSignInButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background = painterResource(R.drawable.background_money)
    val header = stringResource(R.string.welcome_to_app_header)
    val subheader = stringResource(R.string.welcome_to_app_sub_header)
    val whiteColor = colorResource(R.color.white)
    val grayColor = colorResource(R.color.secondary_gray)

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = background,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center)
                .background(color = whiteColor, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = header,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = subheader,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = grayColor,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
            TwoNavigationButtonInRow(
                firstButtonLabel = R.string.sign_in_screen,
                secondButtonLabel = R.string.sign_up_screen,
                onFirstButtonClicked = onSignInButtonClick,
                onSecondButtonClicked = onSignUpButtonClick,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        onSignInButtonClick = { },
        onSignUpButtonClick = { }
    )
}