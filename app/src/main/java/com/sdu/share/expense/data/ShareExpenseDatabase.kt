package com.sdu.share.expense.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sdu.share.expense.data.user.UserDao
import com.sdu.share.expense.models.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class ShareExpenseDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: ShareExpenseDatabase? = null

        fun getDatabase(context: Context): ShareExpenseDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ShareExpenseDatabase::class.java, "share_expense_db")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}