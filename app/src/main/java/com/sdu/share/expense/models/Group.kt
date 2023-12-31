package com.sdu.share.expense.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "groups")
data class Group(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val expenses: MutableList<String>,
    val members: MutableList<String>,
) {

    companion object Factory {
        fun getDefault(): Group = Group(
            id = UUID.randomUUID(),
            name = "",
            expenses = mutableListOf(),
            members = mutableListOf()
        )
    }
}