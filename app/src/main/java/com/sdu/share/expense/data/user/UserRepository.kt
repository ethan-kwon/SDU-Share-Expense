package com.sdu.share.expense.data.user

import com.sdu.share.expense.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)

    fun getUserByUsername(username: String): Flow<User?>
}