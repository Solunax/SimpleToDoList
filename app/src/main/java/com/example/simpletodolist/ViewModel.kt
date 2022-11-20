package com.example.simpletodolist

import androidx.lifecycle.*
import com.example.simpletodolist.room.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: RoomRepository) : androidx.lifecycle.ViewModel(){
    val readData : LiveData<List<ToDo>>
    val checkedData : LiveData<List<ToDo>>

    private var _dateToDoData = MutableLiveData<List<ToDo>>()
    val dateToDoData get() = _dateToDoData

    init {
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