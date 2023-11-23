package com.example.proyectofinal.Interface.TaskScreen.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.Interface.TaskScreen.Repositry.ActivtyRepository
import com.example.proyectofinal.Model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewModel(private val repository: ActivtyRepository = ActivtyRepository()): ViewModel() {
    var contentList: MutableList<TaskModel> = mutableStateListOf<TaskModel>()
    suspend fun getActivities(username: String, category: String){
        viewModelScope.launch(Dispatchers.IO) {
            contentList = repository.getUserActivities(username, category)
        }
    }
    fun addActivity(username: String, listName: String, activity: TaskModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.addActivity(username, listName, activity)
        }
    }

    fun deleteActivity(username: String, listname: String, activity:String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteActivity(username, listname, activity)
        }
    }
}