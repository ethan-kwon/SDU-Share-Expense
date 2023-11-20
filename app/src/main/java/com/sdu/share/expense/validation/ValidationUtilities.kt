package com.sdu.share.expense.validation

import android.util.Patterns

private const val NAME_REGEX = "([A-Z][-,a-z. ']+)+"
private const val USERNAME_REGEX = "[a-zA-Z0-9]+"

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isNameValid(name: String): Boolean {
    return isMatchingRegex(name, NAME_REGEX)
}

fun isUsernameValid(username: String): Boolean {
    return isMatchingRegex(USERNAME_REGEX, username)
}

fun isMatchingRegex(value: String, regex: String): Boolean {
    return regex.toRegex().matches(value)
}

fun isPasswordValid(password: String): Boolean {
    return password.any { it.isDigit() } &&
            password.any { it.isUpperCase() }
}

