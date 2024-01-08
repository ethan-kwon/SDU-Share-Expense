package com.sdu.share.expense.ui.models.group

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.data.expense.ExpenseRepository
import com.sdu.share.expense.data.group.GroupRepository
import com.sdu.share.expense.models.Expense
import com.sdu.share.expense.models.Group
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.rgroup.RGroupViewModelState

class RGroupViewModel(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    var state by mutableStateOf(RGroupViewModelState())
        private set

    val allExpenses: LiveData<List<Expense>> = expenseRepository.getAllExpenses()

    fun onEvent(event: RGroupViewModelEvent) {
        when (event) {
            is RGroupViewModelEvent.ExpenseHasChanged -> {
                state = state.copy(expense = event.expense)
            }

            else -> {}
        }
    }
}

sealed class RGroupViewModelEvent {
    data class ExpenseHasChanged(val expense: Expense) : RGroupViewModelEvent()
}