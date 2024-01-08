package com.sdu.share.expense.data.user

import androidx.lifecycle.LiveData
import com.sdu.share.expense.models.User

interface UserRepository {
    suspend fun insertUser(user: User): User

    fun getUserByUsername(username: String): User?

    fun existsByUsername(username: String): Boolean

    fun getAllUsers(): LiveData<List<User>>

    fun updateUser(user: User)
}