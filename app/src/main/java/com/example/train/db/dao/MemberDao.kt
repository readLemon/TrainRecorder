package com.example.train.db.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.train.db.entity.TeamMemberEntity
import io.reactivex.Completable
import io.reactivex.Flowable

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

    @Query("DELETE FROM TeamMemberBean")
    fun deleteAll(): Completable

    @Query("SELECT * FROM TeamMemberBean")
    fun getAll(): MutableLiveData<List<TeamMemberEntity>>

    @Update
    fun update(member: TeamMemberEntity)

}