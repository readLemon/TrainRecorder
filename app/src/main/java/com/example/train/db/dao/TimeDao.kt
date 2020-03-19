package com.example.train.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.train.db.entity.UnsignedEntity

/**
 * Created by chenyang
 * on 20-3-19
 */
@Dao
interface TimeDao {

    @Insert
    fun insert(entity: UnsignedEntity)

    @Query("DELETE  FROM unsigned_entity")
    fun deleteAll()

    @Query("SELECT * FROM unsigned_entity")
    fun getAll(): LiveData<List<UnsignedEntity>>

}