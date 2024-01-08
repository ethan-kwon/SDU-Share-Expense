package com.sdu.share.expense.ui.models.group

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.data.group.GroupRepository
import com.sdu.share.expense.models.Group
import com.sdu.share.expense.models.User

class GroupViewModel(
    private val groupRepository: GroupRepository
) : ViewModel() {
    var state by mutableStateOf(GroupViewModelState())
        private set

    val allGroups: LiveData<List<Group>> = groupRepository.getAllGroups()

    fun onEvent(event: GroupViewModelEvent) {
        when (event) {
            is GroupViewModelEvent.GroupHasChanged -> {
                state = state.copy(group = event.group)
            }
        }
    }
}

sealed class GroupViewModelEvent {
    data class GroupHasChanged(val group: Group) : GroupViewModelEvent()
}