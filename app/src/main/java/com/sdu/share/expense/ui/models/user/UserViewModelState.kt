package com.sdu.share.expense.ui.models.user

import com.sdu.share.expense.models.User

data class UserViewModelState(
    val user: User = User.getDefault()
)

