package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult

class CredentialsValidator() : BaseValidator<Pair<User?, String>, ValidationResult>("") {
    override fun execute(input: Pair<User?, String>): ValidationResult {
        if (input.first == null || input.first!!.password != input.second) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.invalid_credentials_error)
            )

        }
        return ValidationResult(true)
    }
}