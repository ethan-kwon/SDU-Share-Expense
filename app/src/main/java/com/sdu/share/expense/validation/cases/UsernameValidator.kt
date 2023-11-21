package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isUsernameValid

const val MIN_USERNAME_LENGTH = 3

class UsernameValidator : BaseValidator<String, ValidationResult>("username") {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return returnBlankInputError()
        }
        if (input.length < MIN_USERNAME_LENGTH) {
            return returnInvalidMinNumberOfCharsError(MIN_USERNAME_LENGTH)
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