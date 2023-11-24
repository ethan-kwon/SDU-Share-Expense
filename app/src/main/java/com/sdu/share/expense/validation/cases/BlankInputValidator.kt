package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.validation.ValidationResult

class BlankInputValidator : BaseValidator<String, ValidationResult>("field") {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return returnBlankInputError()
        }
        return ValidationResult(true)
    }
}