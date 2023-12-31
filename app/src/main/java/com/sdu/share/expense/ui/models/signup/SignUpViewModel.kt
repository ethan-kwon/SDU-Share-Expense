package com.sdu.share.expense.ui.models.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.models.User
import com.sdu.share.expense.security.PasswordEncryptor
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.models.user.UserViewModelEvent
import com.sdu.share.expense.validation.cases.EmailValidator
import com.sdu.share.expense.validation.cases.NameValidator
import com.sdu.share.expense.validation.cases.PasswordValidator
import com.sdu.share.expense.validation.cases.RetypedPasswordValidator
import com.sdu.share.expense.validation.cases.UsernameValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(
    private val userRepository: UserRepository,
    private val passwordEncryptor: PasswordEncryptor
) : ViewModel() {
    var formState by mutableStateOf(SignUpViewModelState())
        private set
    var shouldShowPersonalDetailsErrors by mutableStateOf(false)
        private set
    var shouldShowAccountDetailsErrors by mutableStateOf(false)
        private set

    private val nameValidator = NameValidator()
    private val emailValidator = EmailValidator()
    private val usernameValidator = UsernameValidator(userRepository)
    private val passwordValidator = PasswordValidator()
    private val retypedPasswordValidator = RetypedPasswordValidator()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.FirstNameHasChanged -> {
                formState = formState.copy(firstName = event.firstName)
            }

            is SignUpEvent.LastNameHasChanged -> {
                formState = formState.copy(lastName = event.lastName)
            }

            is SignUpEvent.EmailHasChanged -> {
                formState = formState.copy(email = event.email)
            }

            is SignUpEvent.PersonalDetailsScreenCancelButtonClicked -> {
                event.navigateTo()
                resetForm()
            }

            is SignUpEvent.PersonalDetailsScreenNextButtonClicked -> {
                shouldShowPersonalDetailsErrors = true

                if (arePersonalDetailsValid()) {
                    shouldShowPersonalDetailsErrors = false
                    event.navigateTo()
                }
            }

            is SignUpEvent.UsernameHasChanged -> {
                formState = formState.copy(username = event.username)
            }

            is SignUpEvent.PasswordHasChanged -> {
                formState = formState.copy(password = event.password)
            }

            is SignUpEvent.RetypedPasswordHasChanged -> {
                formState = formState.copy(retypedPassword = event.retypedPassword)
            }

            is SignUpEvent.NotificationOptionHasChanged -> {
                formState = formState.copy(shouldSendNotification = event.shouldSendNotifications)
            }

            is SignUpEvent.AccountDataScreenNextButtonClicked -> {
                event.coroutineScope.launch(Dispatchers.Main) {
                    var isFormValid: Boolean

                    withContext(Dispatchers.IO) {
                        isFormValid = validateAccountDetails()
                    }

                    if (isFormValid) {
                        shouldShowAccountDetailsErrors = false
                        event.navigateTo()
                    } else {
                        shouldShowAccountDetailsErrors = true
                    }
                }
            }

            is SignUpEvent.CreateAccountButtonClicked -> {
                onCreateUserEvent(event)
            }
        }
    }

    private fun resetForm() {
        formState = SignUpViewModelState()
        shouldShowPersonalDetailsErrors = false
        shouldShowAccountDetailsErrors = false
    }

    private fun arePersonalDetailsValid(): Boolean {
        return validateFirstName() &&
                validateLastName() &&
                validateEmail()
    }

    private fun validateFirstName(): Boolean {
        val res = nameValidator.execute(formState.firstName)
        formState = formState.copy(firstNameError = res.errorMessage)
        return res.successful
    }

    private fun validateLastName(): Boolean {
        val res = nameValidator.execute(formState.lastName)
        formState = formState.copy(lastNameError = res.errorMessage)
        return res.successful
    }

    private fun validateEmail(): Boolean {
        val res = emailValidator.execute(formState.email)
        formState = formState.copy(emailError = res.errorMessage)
        return res.successful
    }

    private fun validateAccountDetails(): Boolean {
        return validateUsername() &&
                validatePassword() &&
                validateRetypedPassword()
    }

    private fun validateUsername(): Boolean {
        val res = usernameValidator.execute(formState.username)
        formState = formState.copy(usernameError = res.errorMessage)
        return res.successful
    }

    private fun validatePassword(): Boolean {
        val res = passwordValidator.execute(formState.password)
        formState = formState.copy(passwordError = res.errorMessage)
        return res.successful
    }

    private fun validateRetypedPassword(): Boolean {
        val res = retypedPasswordValidator.execute(
            Pair(formState.password, formState.retypedPassword)
        )
        formState = formState.copy(retypedPasswordError = res.errorMessage)
        return res.successful
    }

    private fun onCreateUserEvent(event: SignUpEvent.CreateAccountButtonClicked) {
        var user = User.getDefault()

        event.coroutineScope.launch(Dispatchers.IO) {
            user = saveUser()
        }

        event.userModel.onEvent(UserViewModelEvent.UserHasChanged(user))
        event.navigateTo()
    }

    private suspend fun saveUser(): User {
        encryptPassword()
        return userRepository.insertUser(formState.toUser())
    }

    private fun encryptPassword() {
        formState = formState.copy(
            password = passwordEncryptor.encryptPassword(formState.password)
        )
    }
}

sealed class SignUpEvent {
    data class FirstNameHasChanged(val firstName: String) : SignUpEvent()
    data class LastNameHasChanged(val lastName: String) : SignUpEvent()
    data class EmailHasChanged(val email: String) : SignUpEvent()
    data class PersonalDetailsScreenCancelButtonClicked(val navigateTo: () -> Unit) : SignUpEvent()
    data class PersonalDetailsScreenNextButtonClicked(val navigateTo: () -> Unit) : SignUpEvent()
    data class UsernameHasChanged(val username: String) : SignUpEvent()
    data class PasswordHasChanged(val password: String) : SignUpEvent()
    data class RetypedPasswordHasChanged(val retypedPassword: String) : SignUpEvent()
    data class NotificationOptionHasChanged(val shouldSendNotifications: Boolean) : SignUpEvent()
    data class AccountDataScreenNextButtonClicked(
        val coroutineScope: CoroutineScope,
        val navigateTo: () -> Unit
    ) : SignUpEvent()

    data class CreateAccountButtonClicked(
        val coroutineScope: CoroutineScope,
        val userModel: UserViewModel,
        val navigateTo: () -> Unit
    ) : SignUpEvent()
}