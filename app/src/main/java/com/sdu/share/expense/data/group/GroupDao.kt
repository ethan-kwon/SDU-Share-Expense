package com.sdu.share.expense.data.group

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sdu.share.expense.models.Group
import com.sdu.share.expense.models.User
import java.util.UUID

@Dao
interface GroupDao {
    @Insert
    suspend fun insert(group: Group)

    @Query("SELECT * FROM groups WHERE id = :id")
    fun getGroupByID(id: UUID): Group?

    @Query("SELECT EXISTS(SELECT * FROM groups WHERE id = :id)")
    fun existsByID(id: UUID): Boolean

    @Query("SELECT * FROM groups")
    fun getAllGroups(): LiveData<List<Group>>

    @Query("DELETE FROM groups WHERE id = :id")
    fun deleteID(id: UUID)

    @Update
    fun updateGroup(group: Group)

    @Query("DELETE FROM groups")
    fun deleteAll()
}