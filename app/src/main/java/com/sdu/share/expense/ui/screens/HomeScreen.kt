package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sdu.share.expense.models.Group
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.group.GroupViewModelEvent
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.navigation.components.ShareExpenseTopBar
import java.util.UUID

@Composable
fun HomeScreen(
    userViewModel: UserViewModel,
    groupViewModel: GroupViewModel,
    onNavigateToAdd: () -> Unit,
    onNavigateToGroup: () -> Unit
) {
    val user = userViewModel.state.user
    val groups by groupViewModel.allGroups.observeAsState(emptyList())

    Scaffold(
        topBar = {
            ShareExpenseTopBar(
                title = "Groups",
                canNavigateBack = false,
                navigateUp = { }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(groups) { group ->
                GroupListItem(
                    curUser = user,
                    group = group, groupViewModel,
                    onItemClick = onNavigateToGroup
                )
            }
        }
    }
}

@Composable
fun GroupListItem(curUser: User, group: Group, groupViewModel: GroupViewModel, onItemClick: () -> Unit) {
    if (!group.members.contains(curUser.username)) {
        return
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                groupViewModel.onEvent(GroupViewModelEvent.GroupHasChanged(group))
                onItemClick()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = group.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}