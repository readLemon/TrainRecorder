package com.example.train.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 团队成员名单
 * Created by chenyang
 * on 19-10-23
 */
@Entity
data class TeamMemberModel(
    @PrimaryKey
    @NonNull
    val name: String
)