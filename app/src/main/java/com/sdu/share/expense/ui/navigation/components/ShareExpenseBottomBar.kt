package com.sdu.share.expense.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.sdu.share.expense.ui.navigation.ShareExpenseScreen

sealed class ScreenItem(val screen: ShareExpenseScreen, val itemIcon: ImageVector) {
    object Home : ScreenItem(ShareExpenseScreen.HOME_SCREEN, Icons.Rounded.Home)
    object Profile : ScreenItem(ShareExpenseScreen.PROFILE_SCREEN, Icons.Rounded.AccountCircle)
}

@Composable
fun ShareExpenseBottomBar(
    switchScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        ScreenItem.Home,
        ScreenItem.Profile
    )
    var selectedItem by remember {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, screenItem ->
            NavigationBarItem(
                icon = {
                    Icon(screenItem.itemIcon, contentDescription = null)
                },
                label = {
                    Text(stringResource(screenItem.screen.title))
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    switchScreen()
                }
            )
        }
    }
}