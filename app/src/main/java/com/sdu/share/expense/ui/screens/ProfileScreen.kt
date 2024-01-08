package com.sdu.share.expense.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.sdu.share.expense.models.User
import com.sdu.share.expense.ui.models.profile.ProfileViewModel
import com.sdu.share.expense.ui.models.profile.ProfileViewModelEvent
import com.sdu.share.expense.ui.models.user.UserViewModel
import kotlin.reflect.KFunction2

@Composable
fun ProfileScreen(userViewModel: UserViewModel, profileViewModel: ProfileViewModel) {
    // Collect user data from the ViewModel
    val user = userViewModel.state.user

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
            )
        },
        content = { innerPadding ->
            ProfileContent(user, innerPadding, profileViewModel, userViewModel)
        }
    )
}

@Composable
fun ProfileContent(
    user: User, innerPaddingValues: PaddingValues,
    profileViewModel: ProfileViewModel,
    userViewModel: UserViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(innerPaddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // User details
        ProfileItem(icon = Icons.Default.Person, label = "First Name", value = user.firstName)
        ProfileItem(icon = Icons.Default.Person, label = "Last Name", value = user.lastName)
        ProfileItem(icon = Icons.Default.Email, label = "Email", value = user.email)

        // Notification toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Receive Notifications", modifier = Modifier.weight(1f))

            Switch(
                checked = user.shouldSendNotification,
                onCheckedChange = { isChecked -> profileViewModel.onEvent(
                    ProfileViewModelEvent.NotificationHasChanged(coroutineScope, userViewModel,
                        !isChecked)) }
            )
        }
    }
}

@Composable
fun ProfileItem(icon: ImageVector, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(label, style = MaterialTheme.typography.bodySmall)
            Text(value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
