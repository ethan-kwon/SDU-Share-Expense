package com.sdu.share.expense.ui.models.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.validation.cases.BlankInputValidator

class SignInViewModel : ViewModel() {
    var formState by mutableStateOf(SignInViewModelState())
    var shouldShowErrors by mutableStateOf(false)

    private val blankInputValidator = BlankInputValidator()

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.UsernameHasChanged -> {
                formState = formState.copy(username = event.username)
            }

            is SignInEvent.PasswordHasChanged -> {
                formState = formState.copy(password = event.password)
            }

            is SignInEvent.SignInButtonHasBeenClicked -> {
                if (validateCredentials()) {
                    shouldShowErrors = false
                    event.navigateTo()
                }

                shouldShowErrors = true
            }
        }
    }

    private fun validateCredentials(): Boolean {
        /* TODO Check if credentials are valid*/
        return validateUsername() && validatePassword()
    }

    private fun validateUsername(): Boolean {
        val res = blankInputValidator.execute(formState.username)
        formState = formState.copy(usernameError = res.errorMessage)
        return res.successful
    }

    private fun validatePassword(): Boolean {
        val res = blankInputValidator.execute(formState.password)
        formState = formState.copy(passwordError = res.errorMessage)
        return res.successful
    }
}

sealed class SignInEvent {
    data class UsernameHasChanged(val username: String) : SignInEvent()
    data class PasswordHasChanged(val password: String) : SignInEvent()
    data class SignInButtonHasBeenClicked(val navigateTo: () -> Unit) : SignInEvent()
}