package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sdu.share.expense.ui.models.group.RGroupViewModel


@Composable
fun ExpenseScreen(rGroupViewModel: RGroupViewModel,) {
    val expense = rGroupViewModel.state.expense
    println(expense.description)
    println(expense.members)

    Column {
        Text(text = "This is the expense screen")
        Text(text = "Hi")
    }
}