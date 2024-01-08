package com.sdu.share.expense.data.group

import androidx.lifecycle.LiveData
import com.sdu.share.expense.models.Group
import java.util.UUID

interface GroupRepository {
    suspend fun insertGroup(group: Group): Group

    fun getGroupByID(id: UUID): Group?

    fun existsByID(id: UUID): Boolean

    fun getAllGroups(): LiveData<List<Group>>

    fun updateGroup(group: Group)

    fun deleteID(id: UUID)
}