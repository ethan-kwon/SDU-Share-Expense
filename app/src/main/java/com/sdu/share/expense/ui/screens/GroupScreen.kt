package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sdu.share.expense.AppViewModelProvider
import com.sdu.share.expense.models.Expense
import com.sdu.share.expense.models.Group
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.group.GroupViewModelEvent
import com.sdu.share.expense.ui.models.group.RGroupViewModel
import com.sdu.share.expense.ui.models.group.RGroupViewModelEvent
import com.sdu.share.expense.ui.models.user.UserViewModel
import com.sdu.share.expense.ui.navigation.ShareExpenseScreen
import com.sdu.share.expense.ui.navigation.components.ShareExpenseTopBar

@Composable
fun GroupScreen(
    navController: NavHostController,
    groupViewModel: GroupViewModel,
    rgroupViewModel: RGroupViewModel,
    onNavigateToAdd: () -> Unit,
    onNavigateToExpense: () -> Unit
) {
    val group = groupViewModel.state.group
    val expenses by rgroupViewModel.allExpenses.observeAsState(emptyList())
    var tempExpenses = expenses
    tempExpenses = tempExpenses.filter {
        group.expenses.contains(it.id.toString())
    }
    println(tempExpenses.isEmpty())

    Scaffold(
        topBar = {
            ShareExpenseTopBar(
                title = group.name,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        }
    ) { innerPadding ->
        if (tempExpenses.isEmpty()) {
            Column(verticalArrangement = Arrangement.Center, 
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(innerPadding).fillMaxHeight()) {
                Text(text = "Get started by adding an expense using the '+' button!",
                    fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(tempExpenses) { expense ->
                    ExpenseListItem(
                        expense = expense,
                        group,
                        rgroupViewModel,
                        onItemClick = onNavigateToExpense
                    )
                }
            }
        }
    }
}

@Composable
fun ExpenseListItem(expense: Expense, group: Group, groupViewModel: RGroupViewModel,
                    onItemClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = expense.description,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                )
                Text(
                    text = "${expense.totalCost} DKK",
                    fontWeight = FontWeight.Bold,
                )
            }

            expense.members.forEach { member ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = member,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                    )
                    Text(
                        text = "${expense.totalCost / expense.members.size} DKK",
                    )
                }
            }
        }
    }
}