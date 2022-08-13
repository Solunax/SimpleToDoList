package com.example.simpletodolist.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDoList")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    var index :Int = 0,
    val name : String,
    val date : String,
    val check : Boolean
    )