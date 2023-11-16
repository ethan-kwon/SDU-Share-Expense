package com.sdu.share.expense.models

data class SignUpData(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val retypedPassword: String = "",
    val shouldSendNotification: Boolean = false
)
