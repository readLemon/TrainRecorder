package com.example.train.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.train.db.dao.MemberDao
import com.example.train.model.TeamMemberModel

/**
 * Created by chenyang
 * on 20-1-28
 */
@Database(entities = [TeamMemberModel::class], version = 1)
abstract class MemberDb : RoomDatabase() {

    abstract fun MemberDao(): MemberDao

    companion object {

        var instance: MemberDb? = null

        fun getInstance(context: Context): MemberDb? {
            if (instance == null) {
                synchronized(MemberDb::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext, MemberDb::class.java
                            , "member_database"
                        ).build()
                    }
                }
            }
            return instance
        }
    }


}