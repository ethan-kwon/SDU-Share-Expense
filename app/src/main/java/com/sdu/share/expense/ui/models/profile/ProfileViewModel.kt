package com.sdu.share.expense.ui.models.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun onEvent(event: ProfileViewModelEvent) {
        when (event) {
            is ProfileViewModelEvent.NotificationHasChanged -> {
                event.coroutineScope.launch(Dispatchers.IO) {
                    val user = event.userViewModel.state.user
                    val tempUser = User(user.id, user.firstName, user.lastName, user.email,
                        user.username, user.password, event.shouldSendNotification, user.groups)
                    userRepository.updateUser(tempUser)
                }
            }
        }
    }
}

sealed class ProfileViewModelEvent {
    data class NotificationHasChanged(val coroutineScope: CoroutineScope,
                                      val userViewModel: UserViewModel,
                                      val shouldSendNotification: Boolean): ProfileViewModelEvent()
}
