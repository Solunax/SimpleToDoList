package com.example.simpletodolist

import com.example.simpletodolist.room.AppDataBase
import com.example.simpletodolist.room.ToDo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RoomRepository @Inject constructor(private val db : AppDataBase?) {
    val readData : Flow<List<ToDo>> = db!!.ToDoDAO().getToDoList()
    val checkedData : Flow<List<ToDo>> = db!!.ToDoDAO().getCheckedToDoList()
    fun addToDo(toDo: ToDo){
        db!!.ToDoDAO().addToDo(toDo)
    }

    fun editToDo(toDo: ToDo){
        db!!.ToDoDAO().updateToDo(toDo)
    }

    fun deleteToDO(toDo: ToDo){
        db!!.ToDoDAO().deleteToDo(toDo)
    }

    fun getDateToDo(year :Int, month : Int, date : Int) : List<ToDo>{
        return db!!.ToDoDAO().getDateToDoList(year, month, date)
    }
}