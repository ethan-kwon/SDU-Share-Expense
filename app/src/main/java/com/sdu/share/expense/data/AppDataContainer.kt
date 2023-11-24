package com.sdu.share.expense.data

import android.content.Context
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.data.user.UserRepositoryImpl

interface AppContainer {
    val userRepository: UserRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(ShareExpenseDatabase.getDatabase(context).userDao())
    }
}