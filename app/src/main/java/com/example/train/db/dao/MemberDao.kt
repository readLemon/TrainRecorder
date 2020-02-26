package com.example.train.db.dao

import androidx.room.*
import com.example.train.bean.TeamMemberBean
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by chenyang
 * on 20-1-28
 */
@Dao
interface MemberDao {

    @Insert
    fun insert(memberBean: TeamMemberBean): Completable

    @Delete
    fun deleteAll(members: Array<TeamMemberBean>): Completable

    @Query("DELETE FROM TeamMemberBean")
    fun deleteAll(): Completable

    @Query("SELECT * FROM TeamMemberBean")
    fun getAll(): Flowable<List<TeamMemberBean>>

    @Update
    fun update(member: TeamMemberBean): Completable

}