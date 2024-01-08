package com.sdu.share.expense.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val password: String,
    val shouldSendNotification: Boolean,
    val groups: MutableList<String>
) {
    companion object Factory {
        fun getDefault(): User = User(
            id = 0,
            firstName = "",
            lastName = "",
            email = "",
            username = "",
            password = "",
            shouldSendNotification = false,
            mutableListOf()
        )
    }
}
