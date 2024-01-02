package com.sdu.share.expense.validation.cases

import com.sdu.share.expense.R
import com.sdu.share.expense.models.User
import com.sdu.share.expense.security.PasswordEncryptor
import com.sdu.share.expense.ui.components.UiText
import com.sdu.share.expense.validation.ValidationResult

class CredentialsValidator(
    private val passwordEncryptor: PasswordEncryptor
) : BaseValidator<Pair<User?, String>, ValidationResult>("") {
    override fun execute(input: Pair<User?, String>): ValidationResult {
        if (input.first == null) {
            return InvalidCredentialsError()
        }

        val decryptedPassword = passwordEncryptor.decryptPassword(input.first!!.password)

        if (decryptedPassword != input.second) {
            return InvalidCredentialsError()

        }

        return ValidationResult(true)
    }

    private fun InvalidCredentialsError(): ValidationResult {
        return ValidationResult(
            false,
            UiText.StringResource(R.string.invalid_credentials_error)
        )
    }
}