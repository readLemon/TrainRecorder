package com.example.train.db.dao

import androidx.room.Insert
import com.example.train.db.entity.TeamMemberEntity

/**
 * Created by chenyang
 * on 20-3-23
 */
interface MainDao {
    @Insert
    fun insert(memberEntity: TeamMemberEntity)
}