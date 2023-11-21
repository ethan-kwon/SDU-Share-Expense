package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isNameValid

class NameValidator : BaseValidator<String, ValidationResult>("name") {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return returnBlankInputError()
        }
        if (!isNameValid(input)) {
            return returnInvalidInputError()
        }

        return ValidationResult(true)
    }
}