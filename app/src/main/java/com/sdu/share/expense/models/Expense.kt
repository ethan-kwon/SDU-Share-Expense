package com.sdu.share.expense.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey
    val id: UUID,
    val description: String,
    val totalCost: Int,
    val members: MutableList<String>,
) {
    companion object Factory {
        fun getDefault(): Expense = Expense(
            id = UUID.randomUUID(),
            description = "",
            totalCost = 0,
            members = mutableListOf()
        )
    }
}