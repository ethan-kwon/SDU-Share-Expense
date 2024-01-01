package com.sdu.share.expense.ui.models.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.models.user.UserViewModelEvent
import com.sdu.share.expense.validation.cases.BlankInputValidator
import com.sdu.share.expense.validation.cases.CredentialsValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    var formState by mutableStateOf(SignInViewModelState())
        private set
    var shouldShowErrors by mutableStateOf(false)
        private set

    private val blankInputValidator = BlankInputValidator()
    private val credentialsValidator = CredentialsValidator()
    private var user: User? by mutableStateOf(null)

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.UsernameHasChanged -> {
                formState = formState.copy(username = event.username)
            }

            is SignInEvent.PasswordHasChanged -> {
                formState = formState.copy(password = event.password)
            }

            is SignInEvent.CancelButtonHasBeenClicked -> {
                event.navigateTo()
                resetForm()
            }

            is SignInEvent.SignInButtonHasBeenClicked -> {
                event.coroutineScope.launch(Dispatchers.Main) {
                    withContext(Dispatchers.IO) {
                        retrieveUserByUsername()
                    }

                    if (validateForm()) {
                        shouldShowErrors = false
                        event.userViewModel.onEvent(UserViewModelEvent.UserHasChanged(user!!))
                        event.navigateTo()
                    }

                    shouldShowErrors = true
                }
            }
        }
    }

    private fun resetForm() {
        formState = SignInViewModelState()
        shouldShowErrors = false
    }

    private fun validateForm(): Boolean {
        return validateUsername() &&
                validatePassword() &&
                validateCredentials()
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

    private fun retrieveUserByUsername() {
        user = userRepository.getUserByUsername(formState.username)
    }

    private fun validateCredentials(): Boolean {
        val res = credentialsValidator.execute(Pair(user, formState.password))
        formState = formState.copy(invalidCredentialsError = res.errorMessage)
        return res.successful
    }
}

sealed class SignInEvent {
    data class UsernameHasChanged(val username: String) : SignInEvent()
    data class PasswordHasChanged(val password: String) : SignInEvent()
    data class CancelButtonHasBeenClicked(val navigateTo: () -> Unit) : SignInEvent()
    data class SignInButtonHasBeenClicked(
        val coroutineScope: CoroutineScope,
        val userViewModel: UserViewModel,
        val navigateTo: () -> Unit
    ) : SignInEvent()
}
