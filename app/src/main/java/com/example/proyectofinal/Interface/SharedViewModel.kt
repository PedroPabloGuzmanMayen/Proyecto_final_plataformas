package com.example.proyectofinal.Interface

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.Model.TaskModel
import com.example.proyectofinal.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: UserRepository = UserRepository()): ViewModel() {
    var contentList = mutableStateListOf<TaskModel>()

    var username: String = ""
    var lists: MutableList<String> = mutableStateListOf<String>()

    fun updateLists(username: String) {
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






