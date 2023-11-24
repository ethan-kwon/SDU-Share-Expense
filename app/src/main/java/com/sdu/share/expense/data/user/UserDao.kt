package com.sdu.share.expense.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sdu.share.expense.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): Flow<User?>
}