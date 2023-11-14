package com.example.proyectofinal.Datalayer

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class Repository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getUser(username: String){

        val query = db.collection("Users").document().get().await()
        

    }

    suspend fun getUserList(){

    }

    suspend fun getTask(){

    }

    suspend fun AddTask(){

    }

    suspend fun AddList(){

    }


}