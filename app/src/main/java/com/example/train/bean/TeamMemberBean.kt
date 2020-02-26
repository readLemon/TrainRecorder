package com.example.train.bean

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 团队成员名单
 * Created by chenyang
 * on 19-10-23
 */
@Entity
data class TeamMemberBean(
    @PrimaryKey
    @NonNull
    val name: String
)