package com.example.train.db.dao

import androidx.room.*
import com.example.train.model.TeamMemberModel
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by chenyang
 * on 20-1-28
 */
@Dao
interface MemberDao {

    @Insert
    fun insert(memberModel: TeamMemberModel): Completable

    @Delete
    fun deleteAll(members: Array<TeamMemberModel>): Completable

    @Query("DELETE FROM TeamMemberModel")
    fun deleteAll(): Completable

    @Query("SELECT * FROM TeamMemberModel")
    fun getAll(): Flowable<List<TeamMemberModel>>

    @Update
    fun update(member: TeamMemberModel): Completable

}