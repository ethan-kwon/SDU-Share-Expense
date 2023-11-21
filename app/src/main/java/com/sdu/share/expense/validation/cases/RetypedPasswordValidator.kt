package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult

class RetypedPasswordValidator :
    BaseValidator<Pair<String, String>, ValidationResult>("re-typed password") {
    override fun execute(input: Pair<String, String>): ValidationResult {
        if (input.second.isBlank()) {
            return returnBlankInputError()
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