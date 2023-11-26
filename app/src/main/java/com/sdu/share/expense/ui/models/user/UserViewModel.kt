package com.sdu.share.expense.ui.models.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.models.User

class UserViewModel : ViewModel() {
    var state by mutableStateOf(UserViewModelState())
        private set

    fun onEvent(event: UserViewModelEvent) {
        when (event) {
            is UserViewModelEvent.UserHasChanged -> {
                state = state.copy(user = event.user)
            }
        }
    }
}

sealed class UserViewModelEvent {
    data class UserHasChanged(val user: User) : UserViewModelEvent()
}