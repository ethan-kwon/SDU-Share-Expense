package com.sdu.share.expense.data

import android.content.Context
import com.sdu.share.expense.R
import com.sdu.share.expense.data.expense.ExpenseRepository
import com.sdu.share.expense.data.expense.ExpenseRepositoryImpl
import com.sdu.share.expense.data.group.GroupRepository
import com.sdu.share.expense.data.group.GroupRepositoryImpl
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.data.user.UserRepositoryImpl
import com.sdu.share.expense.models.Expense
import com.sdu.share.expense.security.PasswordEncryptor

interface AppContainer {
    val userRepository: UserRepository
    val passwordEncryptor: PasswordEncryptor
    val groupRepository: GroupRepository
    val expenseRepository: ExpenseRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(ShareExpenseDatabase.getDatabase(context).userDao())
    }
    override val passwordEncryptor: PasswordEncryptor by lazy {
        val secretKey = context.resources.getString(R.string.secret_key)
        val initializationVector = context.resources.getString(R.string.initialization_vector)

        PasswordEncryptor(initializationVector, secretKey)
    }
    override val groupRepository: GroupRepository by lazy {
        GroupRepositoryImpl(ShareExpenseDatabase.getDatabase(context).groupDao())
    }
    override val expenseRepository: ExpenseRepository by lazy {
        ExpenseRepositoryImpl(ShareExpenseDatabase.getDatabase(context).expenseDao())
    }
}