package com.sdu.share.expense.data

import android.content.Context
import com.sdu.share.expense.data.user.UserRepository
import com.sdu.share.expense.data.user.UserRepositoryImpl
import com.sdu.share.expense.security.PasswordEncryptor

interface AppContainer {
    val userRepository: UserRepository
    val passwordEncryptor: PasswordEncryptor
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(ShareExpenseDatabase.getDatabase(context).userDao())
    }
    override val passwordEncryptor: PasswordEncryptor by lazy {
        val secretKey = "F64D2613FFB3FBRE"
        val initializationVector = "e1351a2a08d9c3f5"

        PasswordEncryptor(initializationVector, secretKey)
    }
}