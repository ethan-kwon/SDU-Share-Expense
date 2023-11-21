package com.sdu.share.expense.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.sdu.share.expense.R

@Composable
fun EditTextField(
    @StringRes labelId: Int,
    @DrawableRes leadingIconId: Int,
    errorMessage: UiText?,
    keyboardType: KeyboardType,
    value: String,
    shouldErrorBeDisplayed: Boolean,
    onValueChange: (String) -> Unit,
    isFinalField: Boolean,
    modifier: Modifier = Modifier
) {
    val imeAction = if (isFinalField) ImeAction.Done else ImeAction.Next

    TextField(
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIconId),
                contentDescription = null
            )
        },
        label = { Text(stringResource(labelId)) },
        supportingText = {
            if (shouldErrorBeDisplayed && errorMessage != null) {
                Text(errorMessage.asString())
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        value = value,
        isError = shouldErrorBeDisplayed && errorMessage != null,
        onValueChange = onValueChange,
        modifier = modifier
    )
}

@Composable
fun GenericTextField(
    @StringRes labelId: Int,
    @DrawableRes leadingIconId: Int,
    errorMessage: UiText?,
    value: String,
    shouldErrorBeDisplayed: Boolean,
    onValueChange: (String) -> Unit,
    isFinalField: Boolean,
    modifier: Modifier = Modifier
) {
    EditTextField(
        labelId = labelId,
        leadingIconId = leadingIconId,
        errorMessage = errorMessage,
        keyboardType = KeyboardType.Text,
        value = value,
        shouldErrorBeDisplayed = shouldErrorBeDisplayed,
        onValueChange = onValueChange,
        isFinalField = isFinalField,
        modifier = modifier
    )
}

@Composable
fun UsernameTextField(
    errorMessage: UiText?,
    username: String,
    shouldErrorBeDisplayed: Boolean,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isFinalField: Boolean = false
) {
    GenericTextField(
        labelId = R.string.username_input_label,
        leadingIconId = R.drawable.account_circle_24px,
        errorMessage = errorMessage,
        value = username,
        shouldErrorBeDisplayed = shouldErrorBeDisplayed,
        onValueChange = onUsernameChange,
        isFinalField = isFinalField,
        modifier = modifier
    )
}

@Composable
fun EditPasswordTextField(
    @StringRes labelId: Int,
    @DrawableRes leadingIconId: Int,
    errorMessage: UiText?,
    value: String,
    shouldErrorBeDisplayed: Boolean,
    onValueChange: (String) -> Unit,
    isFinalField: Boolean,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val trailingIconId =
        if (isPasswordVisible) R.drawable.visibility_24px
        else R.drawable.visibility_off_24px
    val trailingIcon = painterResource(trailingIconId)

    val imeAction = if (isFinalField) ImeAction.Done else ImeAction.Next

    val visualTransformation =
        if (!isPasswordVisible) PasswordVisualTransformation()
        else VisualTransformation.None

    TextField(
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIconId),
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }
            ) {
                Icon(
                    painter = trailingIcon,
                    contentDescription = null
                )
            }
        },
        label = { Text(stringResource(labelId)) },
        supportingText = {
            if (shouldErrorBeDisplayed && errorMessage != null) {
                Text(errorMessage.asString())
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        value = value,
        isError = shouldErrorBeDisplayed && errorMessage != null,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        modifier = modifier
    )
}

@Composable
fun PasswordTextField(
    @StringRes labelId: Int,
    errorMessage: UiText?,
    password: String,
    shouldErrorBeDisplayed: Boolean,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isFinalField: Boolean = true
) {
    EditPasswordTextField(
        labelId = labelId,
        leadingIconId = R.drawable.lock_24px,
        errorMessage = errorMessage,
        value = password,
        shouldErrorBeDisplayed = shouldErrorBeDisplayed,
        onValueChange = onPasswordChange,
        isFinalField = isFinalField,
        modifier = modifier
    )
}
