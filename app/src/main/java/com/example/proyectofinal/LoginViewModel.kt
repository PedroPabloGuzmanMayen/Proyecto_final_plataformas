package com.example.proyectofinal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.Model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: LoginRepository = LoginRepository()): ViewModel(){

    suspend fun usersList(): MutableList<String>{
        return withContext(Dispatchers.IO){
            repository.getUsers()
        }
    }

    suspend fun passwordsList(): MutableList<String>{
        return withContext(Dispatchers.IO){
            repository.getPasswords()
        }
    }

}