package com.example.simpletodolist

import android.app.Application
import androidx.lifecycle.*
import com.example.simpletodolist.room.AppDataBase
import com.example.simpletodolist.room.ToDo
import kotlinx.coroutines.*

class ViewModel(application: Application) : AndroidViewModel(application){
    val readData : LiveData<List<ToDo>>
    private val repository : RoomRepository

    private var _dateToDoData = MutableLiveData<List<ToDo>>()
    val dateToDoData get() = _dateToDoData

    init {
        repository = RoomRepository(AppDataBase.getInstance(application))
        readData = repository.readData.asLiveData()
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

    fun getDateToDo(date : String){
        viewModelScope.launch(Dispatchers.IO) {
            _dateToDoData.postValue(repository.getDateToDo(date))
        }
    }
}