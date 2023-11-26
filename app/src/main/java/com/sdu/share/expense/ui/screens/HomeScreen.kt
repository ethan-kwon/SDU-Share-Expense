package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sdu.share.expense.ui.models.user.UserViewModel

@Composable
fun HomeScreen(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    val user = userViewModel.state.user
    val welcomeText = "Hello " + user.firstName + " " + user.lastName

    Column(modifier = modifier) {
        Text("This is home screen")
        Text(welcomeText)
    }
}