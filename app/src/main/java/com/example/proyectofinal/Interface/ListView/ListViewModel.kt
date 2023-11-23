package com.example.proyectofinal.Interface.ListView

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.Model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ListRepository = ListRepository()): ViewModel() {
    var lists: MutableList<String> = mutableStateListOf<String>()
    suspend fun updateLists(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            lists = repository.getUserLists(username)
        }
    }
    fun addList(username: String, listName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addList(username, listName)
        }
    }
}