package com.sdu.share.expense.data.expense

import androidx.lifecycle.LiveData
import com.sdu.share.expense.models.Expense
import java.util.UUID

class ExpenseRepositoryImpl(private val expenseDao: ExpenseDao) : ExpenseRepository {
    override suspend fun insertExpense(expense: Expense): Expense {
        expenseDao.insert(expense)
        return expenseDao.getExpenseById(expense.id)!!
    }

    override fun getExpenseByID(id: UUID): Expense? {
        return expenseDao.getExpenseById(id)
    }

    override fun updateExpense(expense: Expense) {
        return expenseDao.updateExpense(expense)
    }

    override fun existsByID(id: UUID): Boolean {
        return expenseDao.existsByID(id)
    }

    override fun getAllExpenses(): LiveData<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    override fun deleteID(id: UUID) {
        expenseDao.deleteID(id)
    }
}