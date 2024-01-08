package com.sdu.share.expense

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sdu.share.expense.ui.models.addexpense.AddExpenseViewModel
import com.sdu.share.expense.ui.models.addgroup.AddGroupViewModel
import com.sdu.share.expense.ui.models.group.GroupViewModel
import com.sdu.share.expense.ui.models.group.RGroupViewModel
import com.sdu.share.expense.ui.models.profile.ProfileViewModel
import com.sdu.share.expense.ui.models.signin.SignInViewModel
import com.sdu.share.expense.ui.models.signup.SignUpViewModel
import com.sdu.share.expense.ui.models.user.UserViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SignUpViewModel(
                shareExpenseApplication().container.userRepository,
                shareExpenseApplication().container.passwordEncryptor
            )
        }
        initializer {
            UserViewModel()
        }
        initializer {
            SignInViewModel(
                shareExpenseApplication().container.userRepository,
                shareExpenseApplication().container.passwordEncryptor
            )
        }
        initializer {
            GroupViewModel(shareExpenseApplication().container.groupRepository,)
        }
        initializer {
            AddGroupViewModel(
                shareExpenseApplication().container.groupRepository,
                shareExpenseApplication().container.userRepository
            )
        }
        initializer {
            AddExpenseViewModel(
                shareExpenseApplication().container.expenseRepository,
                shareExpenseApplication().container.groupRepository,
                shareExpenseApplication().container.userRepository,
                shareExpenseApplication()
            )
        }
        initializer {
            RGroupViewModel(
                expenseRepository = shareExpenseApplication().container.expenseRepository,
            )
        }
        initializer {
            ProfileViewModel(userRepository = shareExpenseApplication().container.userRepository)
        }
    }
}

fun CreationExtras.shareExpenseApplication(): ShareExpenseApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ShareExpenseApplication)