package com.sdu.share.expense.data.group

import androidx.lifecycle.LiveData
import com.sdu.share.expense.models.Group
import java.util.UUID

class GroupRepositoryImpl(private val groupDao: GroupDao) : GroupRepository {
    override suspend fun insertGroup(group: Group): Group {
        groupDao.insert(group)
        return groupDao.getGroupByID(group.id)!!
    }

    override fun getGroupByID(id: UUID): Group? {
        return groupDao.getGroupByID(id)
    }

    override fun updateGroup(group: Group) {
        return groupDao.updateGroup(group)
    }

    override fun existsByID(id: UUID): Boolean {
        return groupDao.existsByID(id)
    }

    override fun getAllGroups(): LiveData<List<Group>> {
        return groupDao.getAllGroups()
    }

    override fun deleteID(id: UUID) {
        groupDao.deleteID(id)
    }
}