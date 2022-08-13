package com.example.simpletodolist.room

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {
    @Insert(onConflict = IGNORE)
    fun addToDo(toDo : ToDo)

    @Update
    fun updateToDo(toDo : ToDo)

    @Delete
    fun deleteToDo(toDo : ToDo)

    @Query("SELECT * FROM ToDoList ORDER BY 'index' ASC")
    fun getToDoList() : Flow<List<ToDo>>

    @Query("SELECT * FROM ToDoList WHERE date = :date")
    fun getDateToDoList(date:String) : List<ToDo>
}