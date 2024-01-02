package com.sdu.share.expense.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sdu.share.expense.models.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): User?

    @Query("SELECT EXISTS(SELECT * FROM users WHERE username = :username)")
    fun existsByUsername(username: String): Boolean

    @Query("DELETE FROM users")
    fun deleteAll()
}