package com.sdu.share.expense.data.group

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

    override fun existsByID(id: UUID): Boolean {
        return groupDao.existsByID(id)
    }

    override fun deleteID(id: UUID) {
        groupDao.deleteID(id)
    }
}