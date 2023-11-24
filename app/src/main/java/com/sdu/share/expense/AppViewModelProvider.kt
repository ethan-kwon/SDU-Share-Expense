package com.sdu.share.expense

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sdu.share.expense.ui.models.signup.SignUpViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SignUpViewModel(
                shareExpenseApplication().container.userRepository
            )
        }
    }
}

fun CreationExtras.shareExpenseApplication(): ShareExpenseApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ShareExpenseApplication)