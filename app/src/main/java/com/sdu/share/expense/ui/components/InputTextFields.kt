package com.sdu.share.expense.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
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
    val color = colorResource(R.color.light_brown)

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
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            cursorColor = color,
            focusedIndicatorColor = color,
            focusedLabelColor = color,
            focusedLeadingIconColor = color

        ),
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
@Preview
fun GenericTextFieldPreview() {
    GenericTextField(
        labelId = R.string.email_input_label,
        leadingIconId = R.drawable.mail_24px,
        errorMessage = null,
        value = "",
        shouldErrorBeDisplayed = false,
        onValueChange = {},
        isFinalField = false
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

    val color = colorResource(R.color.light_brown)

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
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            cursorColor = color,
            focusedIndicatorColor = color,
            focusedLabelColor = color,
            focusedLeadingIconColor = color,
            focusedTrailingIconColor = color
        ),
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

@Composable
fun TextCheckbox(
    messageText: UiText?,
    isChecked: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val checkboxColor = colorResource(R.color.light_brown)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        if (messageText != null) {
            Text(messageText.asString())
        }
        Checkbox(
            colors = CheckboxDefaults.colors(
                checkedColor = checkboxColor
            ),
            checked = isChecked,
            onCheckedChange = onValueChange
        )
    }
}