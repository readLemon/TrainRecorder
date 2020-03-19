package com.example.train.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.train.db.entity.TeamMemberEntity
import io.reactivex.Completable

/**
 * Created by chenyang
 * on 20-1-28
 */
@Dao
interface MemberDao {

    @Insert
    fun insert(memberEntity: TeamMemberEntity)

    @Delete
    fun deleteAll(members: Array<TeamMemberEntity>)

    @Query("DELETE FROM member_entity")
    fun deleteAll(): Completable

    @Query("SELECT * FROM member_entity")
    fun getAll(): LiveData<List<TeamMemberEntity>>

    @Update
    fun update(member: TeamMemberEntity)

}