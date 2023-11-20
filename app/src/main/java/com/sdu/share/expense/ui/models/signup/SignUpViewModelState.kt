package com.sdu.share.expense.ui.models.signup

import com.sdu.share.expense.ui.components.UiText

data class SignUpViewModelState(
    val firstName: String = "",
    val firstNameError: UiText? = null,

    val lastName: String = "",
    val lastNameError: UiText? = null,

    val email: String = "",
    val emailError: UiText? = null,

    val username: String = "",
    val usernameError: UiText? = null,

    val password: String = "",
    val passwordError: UiText? = null,

    val retypedPassword: String = "",
    val retypedPasswordError: UiText? = null,

    val shouldSendNotification: Boolean = false
)
