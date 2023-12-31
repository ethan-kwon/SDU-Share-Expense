package com.sdu.share.expense.ui.components

import androidx.annotation.ColorRes
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

@Composable
fun TwoNavigationButtonInRow(
    @StringRes firstButtonLabel: Int,
    @StringRes secondButtonLabel: Int,
    onFirstButtonClicked: () -> Unit,
    onSecondButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    @ColorRes outlinedButtonColor: Int = R.color.light_brown,
    @ColorRes filledButtonBackgroundColor: Int = R.color.light_brown,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        OutlinedButton(
            onClick = onFirstButtonClicked,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colorResource(outlinedButtonColor)
            ),
            border = BorderStroke(1.5.dp, colorResource(outlinedButtonColor))
        ) {
            Text(stringResource(firstButtonLabel))
        }
        Button(
            onClick = onSecondButtonClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(filledButtonBackgroundColor)
            )
        ) {
            Text(stringResource(secondButtonLabel))
        }
    }
}