package com.example.simpletodolist.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun ToDoDAO() : ToDoDAO
}