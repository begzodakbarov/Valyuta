package com.example.valyuta.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.valyuta.models.MainValyuta

@Database(entities = [MainValyuta::class], version = 1)
abstract class DbHelper : RoomDatabase() {

    abstract fun myDao():DbInterface

    companion object{
        var instance : DbHelper? = null

        @Synchronized
        fun getInstance(context: Context):DbHelper{
            if (instance == null){
                instance = Room.databaseBuilder(context, DbHelper::class.java,"db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance!!
        }
    }
}