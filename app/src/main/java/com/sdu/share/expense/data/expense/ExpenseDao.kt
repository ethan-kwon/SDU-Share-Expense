package com.sdu.share.expense.data.expense

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sdu.share.expense.models.Expense
import com.sdu.share.expense.models.User
import java.util.UUID

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(expense: Expense)

    @Query("SELECT * FROM expenses WHERE id = :id")
    fun getExpenseById(id: UUID): Expense?

    @Query("SELECT EXISTS(SELECT * FROM expenses WHERE id = :id)")
    fun existsByID(id: UUID): Boolean

    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Query("DELETE FROM expenses WHERE id = :id")
    fun deleteID(id: UUID)

    @Update
    fun updateExpense(expense: Expense)

    @Query("DELETE FROM expenses")
    fun deleteAll()
}