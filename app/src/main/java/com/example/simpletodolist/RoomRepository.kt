package com.example.simpletodolist

import com.example.simpletodolist.room.AppDataBase
import com.example.simpletodolist.room.ToDo
import kotlinx.coroutines.flow.Flow

class RoomRepository(private val db : AppDataBase?) {
    val readData : Flow<List<ToDo>> = db!!.ToDoDAO().getToDoList()

    fun addToDo(toDo: ToDo){
        db!!.ToDoDAO().addToDo(toDo)
    }

    fun editToDo(toDo: ToDo){
        db!!.ToDoDAO().updateToDo(toDo)
    }

    fun deleteToDO(toDo: ToDo){
        db!!.ToDoDAO().deleteToDo(toDo)
    }

    fun getDateToDo(date:String) : List<ToDo>{
        return db!!.ToDoDAO().getDateToDoList(date)
    }
}