package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isNameValid

class ValidationNameCase : ValidationBaseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.field_cannot_be_blank_error, "name")
            )
        }
        if (!isNameValid(input)) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.field_is_invalid_error, "name")
            )
        }

        return ValidationResult(true)
    }
}