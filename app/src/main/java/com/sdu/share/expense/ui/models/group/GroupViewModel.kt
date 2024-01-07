package com.sdu.share.expense.ui.models.group

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sdu.share.expense.models.Group

class GroupViewModel : ViewModel() {
    var state by mutableStateOf(GroupViewModelState())
        private set

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