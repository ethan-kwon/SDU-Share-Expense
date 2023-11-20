package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isUsernameValid

const val MIN_USERNAME_LENGTH = 3

class ValidationUsernameCase : ValidationBaseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.field_cannot_be_blank_error, "username")
            )
        }
        if (input.length < MIN_USERNAME_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.field_needs_at_least_x_chars_error,
                    "username",
                    MIN_USERNAME_LENGTH
                ),
            )
        }

        if (!isUsernameValid(input)) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.username_is_invalid_error)
            )
        }
        /*TODO: Check if username is already taken*/
        return ValidationResult(true)
    }
}