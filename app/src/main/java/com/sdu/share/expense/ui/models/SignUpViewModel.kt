package com.sdu.share.expense.ui.models

import androidx.lifecycle.ViewModel
import com.sdu.share.expense.models.SignUpData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpData())
    val uiState: StateFlow<SignUpData> = _uiState.asStateFlow()

    fun setPersonalDetails(
        firstName: String,
        lastName: String,
        email: String
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                firstName = firstName,
                lastName = lastName,
                email = email
            )
        }
    }

    fun setAccountDetails(
        username: String,
        password: String,
        retypedPassword: String,
        shouldSendNotification: Boolean
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                username = username,
                password = password,
                retypedPassword = retypedPassword,
                shouldSendNotification = shouldSendNotification
            )
        }
    }

    fun resetSignUpData() {
        _uiState.value = SignUpData()
    }
}