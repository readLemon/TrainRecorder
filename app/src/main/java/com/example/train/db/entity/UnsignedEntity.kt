package com.example.train.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull

/**
 * Created by chenyang
 * on 20-3-19
 */
@Entity(tableName = "unsigned_entity")
data class UnsignedEntity(
    @PrimaryKey
    @NonNull
    val name: String
) {

}