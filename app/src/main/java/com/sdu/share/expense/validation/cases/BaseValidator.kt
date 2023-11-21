package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult

abstract class BaseValidator<In, Out>(protected val fieldName: String) {

    abstract fun execute(input: In): Out

    protected fun returnBlankInputError(): ValidationResult {
        return ValidationResult(
            false, UiText.StringResource(R.string.field_cannot_be_blank_error, fieldName)
        )
    }

    protected fun returnInvalidInputError(): ValidationResult {
        return ValidationResult(
            false, UiText.StringResource(R.string.field_is_invalid_error, fieldName)
        )
    }

    protected fun returnInvalidMinNumberOfCharsError(minCharsNo: Int): ValidationResult {
        return ValidationResult(
            false,
            UiText.StringResource(
                R.string.field_needs_at_least_x_chars_error, fieldName, minCharsNo
            )
        )
    }
}