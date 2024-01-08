package com.sdu.share.expense.ui.models.addgroup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdu.share.expense.data.group.GroupRepository
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddGroupViewModel (
    private val groupRepository: GroupRepository,
    private val userRepository: UserRepository
): ViewModel() {
    var formState by mutableStateOf(AddGroupViewModelState())
        private set
    var showNameError by mutableStateOf(true)
        private set

    val allUsers: LiveData<List<User>> = userRepository.getAllUsers()

    private val regex = "[-_a-zA-Z0-9][-_a-zA-Z0-9 ]*"

    fun onEvent(event: AddGroupState) {
        when (event) {
            is AddGroupState.NameHasChanged -> {
                formState = formState.copy(name = event.name)
                showNameError = !regex.toRegex().matches(formState.name)
            }
            is AddGroupState.CreateGroupButtonClicked -> {
                if (!showNameError && formState.checkedUsers.isNotEmpty()) {
                    val usernames: MutableList<String> = mutableListOf()
                    formState.checkedUsers.forEach {
                        user -> usernames.add(user.username)
                    }
                    usernames.add(event.userModel.state.user.username)
                    val group = formState.toGroup(usernames)
                    formState.checkedUsers.forEach { user ->
                        user.groups.add(group.id.toString())
                    }

                    event.coroutineScope.launch(Dispatchers.IO) {
                        groupRepository.insertGroup(group = group)
                        formState.checkedUsers.forEach { user ->
                            userRepository.updateUser(user)
                        }
                    }
                    event.navigateTo()
                }
            }
            is AddGroupState.UserChecked -> {
                if (formState.checkedUsers.contains(event.username)) {
                    formState.checkedUsers.remove(event.username)
                } else {
                    formState.checkedUsers.add(event.username)
                }
            }
        }
    }

}

sealed class AddGroupState {
    data class NameHasChanged(val name: String): AddGroupState()
    data class CreateGroupButtonClicked(
        val coroutineScope: CoroutineScope,
        val userModel: UserViewModel,
        val navigateTo: () -> Unit
    ) : AddGroupState()

    data class UserChecked(val username: User): AddGroupState()
}