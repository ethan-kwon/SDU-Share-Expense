package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isPasswordValid

const val MIN_PASSWORD_LENGTH = 6

class ValidationPasswordCase : ValidationBaseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.length < MIN_PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(
                    R.string.field_needs_at_least_x_chars_error,
                    "password",
                    MIN_PASSWORD_LENGTH
                ),
            )
        }

        if (!isPasswordValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.password_needs_one_letter_and_digit_error),
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}