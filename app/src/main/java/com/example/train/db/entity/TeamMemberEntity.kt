package com.example.train.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 团队成员名单
 * Created by chenyang
 * on 19-10-23
 */
@Entity(tableName = "member_entity")
data class TeamMemberEntity(
    @PrimaryKey
    @NonNull
    val name: String
)