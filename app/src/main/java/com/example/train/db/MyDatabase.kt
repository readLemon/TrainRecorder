package com.example.train.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.train.App
import com.example.train.db.dao.MemberDao
import com.example.train.db.entity.TeamMemberEntity

/**
 * Created by chenyang
 * on 20-1-28
 */
@Database(entities = [TeamMemberEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun MemberDao(): MemberDao

    companion object {

        private var instance: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            if (instance == null) {
                synchronized(MyDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext, MyDatabase::class.java
                            , "member_database"
                        ).build()
                    }
                }
            }
            return instance as MyDatabase
        }
    }
}

fun getDatabase(): MyDatabase{
    return MyDatabase.getInstance(App.context)
}