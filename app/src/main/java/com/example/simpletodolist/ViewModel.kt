package com.example.simpletodolist

import android.app.Application
import androidx.lifecycle.*
import com.example.simpletodolist.room.AppDataBase
import com.example.simpletodolist.room.ToDo
import kotlinx.coroutines.*

class ViewModel(application: Application) : AndroidViewModel(application){
    val readData : LiveData<List<ToDo>>
     val checkedData : LiveData<List<ToDo>>
    private val repository : RoomRepository

    private var _dateToDoData = MutableLiveData<List<ToDo>>()
    val dateToDoData get() = _dateToDoData

    init {
        repository = RoomRepository(AppDataBase.getInstance(application))
        readData = repository.readData.asLiveData()
        checkedData = repository.checkedData.asLiveData()
    }

    fun addToDo(toDo : ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToDo(toDo)
        }
    }

    fun editToDo(toDo : ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.editToDo(toDo)
        }
    }

    fun deleteToDo(toDo: ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteToDO(toDo)
        }
    }

    fun getDateToDo(year : Int, month : Int, date : Int){
        viewModelScope.launch(Dispatchers.IO) {
            _dateToDoData.postValue(repository.getDateToDo(year, month, date))
        }
    }
}