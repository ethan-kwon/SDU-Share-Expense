package com.sdu.share.expense.data.expense

import androidx.lifecycle.LiveData
import com.sdu.share.expense.models.Expense
import java.util.UUID

interface ExpenseRepository {
    suspend fun insertExpense(expense: Expense): Expense

    fun getExpenseByID(id: UUID): Expense?

    fun existsByID(id: UUID): Boolean

    fun getAllExpenses(): LiveData<List<Expense>>

    fun updateExpense(expense: Expense)

    fun deleteID(id: UUID)
}