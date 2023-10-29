package com.example.proyectofinal.Interface

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.Task.Task

class SharedViewModel: ViewModel() {
    val contentList = mutableStateListOf<Task>()

}