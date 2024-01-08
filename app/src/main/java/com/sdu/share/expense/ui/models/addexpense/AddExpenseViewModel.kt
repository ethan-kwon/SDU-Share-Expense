package com.sdu.share.expense.ui.models.addexpense

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.data.expense.ExpenseRepository
import com.sdu.share.expense.data.group.GroupRepository
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.addgroup.AddGroupState
import com.sdu.share.expense.ui.models.addgroup.AddGroupViewModel
import com.sdu.share.expense.ui.models.addgroup.toGroup
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddExpenseViewModel (
    private val expenseRepository: ExpenseRepository,
    private val groupRepository: GroupRepository,
    private val userRepository: UserRepository, application: Application
): AndroidViewModel(application) {
    var formState by mutableStateOf(AddExpenseViewModelState())
        private set

    val allUsers: LiveData<List<User>> = userRepository.getAllUsers()

    fun onEvent(event: AddExpenseState) {
        when (event) {
            is AddExpenseState.DescriptionHasChanged -> {
                formState = formState.copy(description = event.description)
            }
            is AddExpenseState.UserChecked -> {
                if (formState.checkedUsers.contains(event.user.username)) {
                    formState.checkedUsers.remove(event.user.username)
                } else {
                    formState.checkedUsers.add(event.user.username)
                }
            }
            is AddExpenseState.CreateExpenseButtonClicked -> {
                if (formState.checkedUsers.isNotEmpty()) {
                    val expense = formState.toExpense()
                    val group = event.groupViewModel.state.group
                    group.expenses.add(expense.id.toString())

                    event.coroutineScope.launch(Dispatchers.IO) {
                        expenseRepository.insertExpense(expense = expense)
                        groupRepository.updateGroup(group)
                    }
                    val intent = Intent()
                    event.navigateTo()
                }
            }
            is AddExpenseState.TotalCostHasChanged -> {
                formState = formState.copy(totalCost = event.totalCost)
            }
        }
    }

}

sealed class AddExpenseState {
    data class DescriptionHasChanged(val description: String): AddExpenseState()
    data class CreateExpenseButtonClicked(
        val coroutineScope: CoroutineScope,
        val groupViewModel: GroupViewModel,
        val navigateTo: () -> Unit
    ) : AddExpenseState()

    data class UserChecked(val user: User): AddExpenseState()
    data class TotalCostHasChanged(val totalCost: String): AddExpenseState()
}