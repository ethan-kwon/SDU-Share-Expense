package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isEmailValid

class EmailValidator : BaseValidator<String, ValidationResult>("email") {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return returnBlankInputError()
        }
        if (!isEmailValid(input)) {
            return returnInvalidInputError()
        }

        return ValidationResult(true)
    }
}