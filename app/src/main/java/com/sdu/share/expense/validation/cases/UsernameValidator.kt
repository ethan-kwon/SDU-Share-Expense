package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult
import com.sdu.share.expense.validation.isUsernameValid

const val MIN_USERNAME_LENGTH = 3

class UsernameValidator(private val userRepository: UserRepository) :
    BaseValidator<String, ValidationResult>("username") {
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
        if (userRepository.existsByUsername(input)) {
            return ValidationResult(
                false,
                UiText.StringResource(R.string.username_is_already_taken_error)
            )
        }

        return ValidationResult(true)
    }
}