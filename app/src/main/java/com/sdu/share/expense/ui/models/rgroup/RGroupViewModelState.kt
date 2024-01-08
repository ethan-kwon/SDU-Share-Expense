package com.sdu.share.expense.ui.models.rgroup

import com.sdu.share.expense.models.Expense
import com.sdu.share.expense.models.Group

data class RGroupViewModelState(
    val expense: Expense= Expense.getDefault()
)