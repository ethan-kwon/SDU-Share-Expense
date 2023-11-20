package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isEmailValid

class ValidationEmailCase : ValidationBaseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.field_cannot_be_blank_error, "email")
            )
        }
        if (!isEmailValid(input)) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.field_is_invalid_error, "email")
            )
        }

        return ValidationResult(true)
    }
}