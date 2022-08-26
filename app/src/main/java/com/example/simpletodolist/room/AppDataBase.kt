package com.example.simpletodolist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ToDo::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun ToDoDAO() : ToDoDAO

    companion object{
        private var instance : AppDataBase? = null

        fun getInstance(context: Context) : AppDataBase?{
            if(instance == null){
                synchronized(AppDataBase::class){
                    instance = Room.databaseBuilder(context, AppDataBase::class.java, "ToDo").build()
                }
            }
            return instance
        }
    }
}