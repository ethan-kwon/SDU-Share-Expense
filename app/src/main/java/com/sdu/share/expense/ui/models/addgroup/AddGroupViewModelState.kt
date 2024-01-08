package com.sdu.share.expense.ui.models.addgroup

import com.sdu.share.expense.models.Group
import com.sdu.share.expense.models.User
import java.util.UUID

data class AddGroupViewModelState (
    val name: String = "",
    val checkedUsers: MutableList<User> = mutableListOf()
)

fun AddGroupViewModelState.toGroup(usernames: MutableList<String>): Group = Group(
    id = UUID.randomUUID(),
    name = name,
    expenses = mutableListOf(),
    members = usernames
)