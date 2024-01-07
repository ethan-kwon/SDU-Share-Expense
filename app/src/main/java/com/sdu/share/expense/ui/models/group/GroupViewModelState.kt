package com.sdu.share.expense.ui.models.group

import com.sdu.share.expense.models.Group

data class GroupViewModelState(
    val group: Group = Group.getDefault()
)