package com.sdu.share.expense.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TwoNavigationButtonInRow(
    @StringRes firstButtonLabel: Int,
    @StringRes secondButtonLabel: Int,
    onFirstButtonClicked: () -> Unit,
    onSecondButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        OutlinedButton(
            onClick = onFirstButtonClicked
        ) {
            Text(stringResource(firstButtonLabel))
        }
        Button(
            onClick = onSecondButtonClicked
        ) {
            Text(stringResource(secondButtonLabel))
        }
    }
}