package com.sdu.share.expense.data.user

import com.sdu.share.expense.models.User

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: User): User {
        userDao.insert(user)
        return userDao.getUserByUsername(user.username)!!
    }

    override fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    override fun existsByUsername(username: String): Boolean {
        return userDao.existsByUsername(username)
    }
}