package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult

class ValidationRetypedPasswordCase : ValidationBaseCase<Pair<String, String>, ValidationResult> {
    override fun execute(input: Pair<String, String>): ValidationResult {
        if (input.second.isBlank()) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.field_cannot_be_blank_error, "re-typed password")
            )
        }

        if (input.first != input.second) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.passwords_do_not_match_error)
            )
        }

        return ValidationResult(true)
    }
}