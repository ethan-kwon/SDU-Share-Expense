package com.sdu.share.expense.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sdu.share.expense.R
import com.sdu.share.expense.data.group.GroupDao
import com.sdu.share.expense.data.user.UserDao
import com.sdu.share.expense.models.User
import com.sdu.share.expense.models.Group
import com.sdu.share.expense.security.PasswordEncryptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(
    entities = [User::class, Group::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ShareExpenseDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun groupDao(): GroupDao

    companion object {
        @Volatile
        private var Instance: ShareExpenseDatabase? = null

        fun getDatabase(context: Context): ShareExpenseDatabase {
            val database = Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ShareExpenseDatabase::class.java, "share_expense_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }

            val userInitializer = UserDatabaseInitializer(context, database.userDao())
            userInitializer.afterDatabaseCreate()

            return database
        }
    }
}

class UserDatabaseInitializer(
    context: Context,
    private val userDao: UserDao
) {
    private val initializationVector = context.resources.getString(R.string.initialization_vector)
    private val secretKey = context.resources.getString(R.string.secret_key)
    private val passwordEncryptor = PasswordEncryptor(initializationVector, secretKey)

    private val applicationScope = CoroutineScope(SupervisorJob())

    fun afterDatabaseCreate() {
        applicationScope.launch(Dispatchers.IO) {
            userDao.deleteAll()
            prepopulateDatabase()
        }
    }

    private suspend fun prepopulateDatabase() {
        val users = listOf(
            User(
                0,
                "John",
                "Doe",
                "john.doe@gmail.com",
                "JohnDoe",
                passwordEncryptor.encryptPassword("Password11"),
                false,
                mutableListOf()
            ),
            User(
                0,
                "Jane",
                "Smith",
                "jane.smith@microsoft.com",
                "JaneX",
                passwordEncryptor.encryptPassword("Password11"),
                true,
                mutableListOf()
            ),
            User(
                0,
                "Darth",
                "Vader",
                "darth.vader@email.com",
                "Vader",
                passwordEncryptor.encryptPassword("abc"),
                false,
                mutableListOf()
            ),
        )

        users.forEach {
            userDao.insert(it)
        }
    }
}