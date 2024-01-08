package com.sdu.share.expense.data.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sdu.share.expense.models.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): User?

    @Query("SELECT EXISTS(SELECT * FROM users WHERE username = :username)")
    fun existsByUsername(username: String): Boolean

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM users")
    fun deleteAll()
}