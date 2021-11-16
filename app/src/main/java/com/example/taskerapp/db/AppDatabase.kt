package com.example.taskerapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskerapp.db.model.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "db")
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase!!
        }
    }

}