package com.sdu.share.expense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sdu.share.expense.ui.screens.ShareExpenseApp
import com.sdu.share.expense.ui.theme.SduShareExpenseTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SduShareExpenseTheme {
                ShareExpenseApp()
            }
        }
    }
}
