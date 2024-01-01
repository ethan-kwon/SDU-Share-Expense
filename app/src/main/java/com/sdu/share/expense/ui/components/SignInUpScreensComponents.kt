package com.sdu.share.expense.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdu.share.expense.R

@Composable
fun MoneyBackgroundBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val background = painterResource(R.drawable.background_money)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = background,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}

@Composable
fun ContentFrame(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val whiteColor = colorResource(R.color.white)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = whiteColor, shape = RoundedCornerShape(8.dp))
    ) {
        content()
    }
}

@Composable
fun MoneyBackgroundContentFrame(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    MoneyBackgroundBox {
        ContentFrame(
            modifier = modifier
        ) {
            content()
        }
    }
}

@Composable
fun MoneyBackgroundPreContentFrame(
    headerTitle: UiText,
    subheaderTitle: UiText?,
    buttonsParams: TwoNavigationButtonsParams,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val grayColor = colorResource(R.color.secondary_gray)

    MoneyBackgroundContentFrame(
        modifier = modifier
    ) {
        Text(
            text = headerTitle.asString(),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 20.dp)
        )
        if (subheaderTitle != null) {
            Text(
                text = subheaderTitle.asString(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = grayColor,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
        }

        content()

        TwoNavigationButtonInRow(
            params = buttonsParams,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ErrorMessage(
    errorMessageContent: UiText,
    modifier: Modifier = Modifier
) {
    val redColor = colorResource(R.color.red_error_message)

    Text(
        text = errorMessageContent.asString(),
        color = redColor,
        modifier = modifier
    )
}

@Composable
fun SignUpSummaryItem(
    @DrawableRes leadingIconId: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 2.dp)
        ) {
            Icon(
                painter = painterResource(leadingIconId),
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = value
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Divider(thickness = 1.dp)
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpSummaryItemPreview() {
    SignUpSummaryItem(
        leadingIconId = R.drawable.badge_24px,
        value = "John"
    )
}