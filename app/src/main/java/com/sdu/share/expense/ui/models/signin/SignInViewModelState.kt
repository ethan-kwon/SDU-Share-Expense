package com.sdu.share.expense.ui.models.signin

import com.sdu.share.expense.ui.components.UiText

data class SignInViewModelState(
    val username: String = "",
    val usernameError: UiText? = null,

    val password: String = "",
    val passwordError: UiText? = null
)