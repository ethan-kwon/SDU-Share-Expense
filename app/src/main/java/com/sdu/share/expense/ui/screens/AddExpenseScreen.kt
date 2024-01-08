package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sdu.share.expense.AppViewModelProvider
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.addexpense.AddExpenseState
import com.sdu.share.expense.ui.models.addexpense.AddExpenseViewModel
import com.sdu.share.expense.ui.models.addgroup.AddGroupState
import com.sdu.share.expense.ui.models.addgroup.AddGroupViewModel
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel

@Composable
fun AddExpenseScreen(navController: NavHostController,
                     groupViewModel: GroupViewModel,
                     userViewModel: UserViewModel,
                     addExpenseViewModel: AddExpenseViewModel =
                         viewModel(factory = AppViewModelProvider.Factory)
) {
    val users by addExpenseViewModel.allUsers.observeAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AddGroupTopBar(
                title = "Add New Group",
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onAddClick = { addExpenseViewModel.onEvent(
                    AddExpenseState.CreateExpenseButtonClicked(
                    coroutineScope = coroutineScope,
                    navigateTo = { navController.navigateUp() },
                    groupViewModel = groupViewModel
                )) })
        }
    ) { innerPadding ->
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                TextField(
                    value = addExpenseViewModel.formState.description, onValueChange = {
                        addExpenseViewModel.onEvent(AddExpenseState.DescriptionHasChanged(it))
                    },
                    label = { Text(text = "Description") },
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
                TextField(value = addExpenseViewModel.formState.totalCost, onValueChange = {
                    addExpenseViewModel.onEvent(AddExpenseState.TotalCostHasChanged(it))
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(top=4.dp),
                    label = { Text(text = "Total Cost (in DKK)") })
                LazyColumn {
                    items(users) { user ->
                        ExpenseUserListItem(user = user, userViewModel,
                            groupViewModel = groupViewModel,
                            addExpenseViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseUserListItem(user: User, userViewModel: UserViewModel, groupViewModel: GroupViewModel,
                        addExpenseViewModel: AddExpenseViewModel) {
    var isChecked by remember { mutableStateOf(false) }
    if (groupViewModel.state.group.members.contains(user.username))

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
                addExpenseViewModel.onEvent(AddExpenseState.UserChecked(user))
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
