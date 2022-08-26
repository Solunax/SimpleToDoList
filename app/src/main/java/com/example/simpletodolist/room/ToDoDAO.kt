package com.example.simpletodolist.room

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import kotlinx.coroutines.flow.Flow
import java.time.Month

@Dao
interface ToDoDAO {
    @Insert(onConflict = IGNORE)
    fun addToDo(toDo : ToDo)

    @Update
    fun updateToDo(toDo : ToDo)

    @Delete
    fun deleteToDo(toDo : ToDo)

    @Query("SELECT * FROM ToDoList ORDER BY year DESC, month DESC, date DESC")
    fun getToDoList() : Flow<List<ToDo>>

    @Query("SELECT * FROM ToDoList WHERE year = :year AND month = :month AND date = :date")
    fun getDateToDoList(year:Int, month: Int, date : Int) : List<ToDo>

    @Query("SELECT * FROM ToDoList WHERE `check` ORDER BY year ASC, month ASC, date ASC")
    fun getCheckedToDoList() : Flow<List<ToDo>>
}