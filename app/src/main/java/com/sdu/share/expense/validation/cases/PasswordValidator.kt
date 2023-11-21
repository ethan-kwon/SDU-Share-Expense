package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isPasswordValid

const val MIN_PASSWORD_LENGTH = 6

class PasswordValidator : BaseValidator<String, ValidationResult>("password") {
    override fun execute(input: String): ValidationResult {
        if (input.length < MIN_PASSWORD_LENGTH) {
            return returnInvalidMinNumberOfCharsError(MIN_PASSWORD_LENGTH)
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