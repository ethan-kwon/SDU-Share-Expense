package com.sdu.share.expense.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sdu.share.expense.R


data class TwoNavigationButtonsParams(
    @StringRes val firstButtonLabel: Int = 0,
    @StringRes val secondButtonLabel: Int = 0,
    val onFirstButtonClicked: () -> Unit = {},
    val onSecondButtonClicked: () -> Unit = {}
)

@Composable
fun TwoNavigationButtonInRow(
    params: TwoNavigationButtonsParams,
    modifier: Modifier = Modifier
) {
    val buttonColor = colorResource(R.color.light_brown)

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        OutlinedButton(
            onClick = params.onFirstButtonClicked,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = buttonColor
            ),
            border = BorderStroke(1.5.dp, buttonColor)
        ) {
            Text(stringResource(params.firstButtonLabel))
        }
        Button(
            onClick = params.onSecondButtonClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor
            )
        ) {
            Text(stringResource(params.secondButtonLabel))
        }
    }
}