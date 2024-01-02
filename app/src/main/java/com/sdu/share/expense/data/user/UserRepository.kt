package com.sdu.share.expense.data.user

import com.sdu.share.expense.models.User

interface UserRepository {
    suspend fun insertUser(user: User): User

    fun getUserByUsername(username: String): User?

    fun existsByUsername(username: String): Boolean
}