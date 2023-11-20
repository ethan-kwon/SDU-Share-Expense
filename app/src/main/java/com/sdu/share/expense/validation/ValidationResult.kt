package com.sdu.share.expense.validation

import com.sdu.share.expense.ui.components.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)