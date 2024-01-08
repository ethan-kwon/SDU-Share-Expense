package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.sdu.share.expense.AppViewModelProvider
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.addgroup.AddGroupState
import com.sdu.share.expense.ui.models.addgroup.AddGroupViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel



@Composable
fun AddGroupScreen(
    navController: NavHostController,
    userViewModel: UserViewModel,
    addGroupViewModel: AddGroupViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val users by addGroupViewModel.allUsers.observeAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AddGroupTopBar(
                title = "Add New Group",
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onAddClick = { addGroupViewModel.onEvent(AddGroupState.CreateGroupButtonClicked(
                    coroutineScope = coroutineScope,
                    navigateTo = { navController.navigateUp() },
                    userModel = userViewModel
                )) })
        }
    ) { innerPadding ->
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                TextField(
                    value = addGroupViewModel.formState.name, onValueChange = {
                        addGroupViewModel.onEvent(AddGroupState.NameHasChanged(it))
                    },
                    label = { Text(text = "Group Name") },
                    isError = addGroupViewModel.showNameError, singleLine = true,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
                LazyColumn {
                    items(users) { user ->
                        UserListItem(user = user, userViewModel, groupViewModel = addGroupViewModel)
                    }
                }
            }
        }
    }

}

@Composable
fun AddGroupTopBar(title: String, canNavigateBack: Boolean,
                   navigateUp: () -> Unit,
                   modifier: Modifier = Modifier,
                   onAddClick: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            }
        },
        actions = {
            AddIconButton(onAddClick = onAddClick)
        }
    )
}

@Composable
fun AddIconButton(onAddClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .size(48.dp)
            .clip(CircleShape)
            .clickable(onClick = onAddClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = Color.Black
        )
    }
}

@Composable
fun UserListItem(user: User, userViewModel: UserViewModel, groupViewModel: AddGroupViewModel) {
    var isChecked by remember { mutableStateOf(false) }
    if (user.username == userViewModel.state.user.username) {
        return
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                isChecked = !isChecked
            }
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                groupViewModel.onEvent(AddGroupState.UserChecked(user))
            },
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = "${user.firstName} ${user.lastName}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = user.username)
            // Add other user information as needed
        }
    }
}
