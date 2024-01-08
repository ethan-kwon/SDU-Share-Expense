package com.sdu.share.expense.ui.models.addexpense

import com.sdu.share.expense.models.Expense
import com.sdu.share.expense.models.User
import java.util.UUID

data class AddExpenseViewModelState (
    val totalCost: String = "",
    val description: String = "",
    val checkedUsers: MutableList<String> = mutableListOf()
)

fun AddExpenseViewModelState.toExpense(): Expense = Expense(
    id = UUID.randomUUID(),
    description = description,
    totalCost = totalCost.toInt(),
    members = checkedUsers
)