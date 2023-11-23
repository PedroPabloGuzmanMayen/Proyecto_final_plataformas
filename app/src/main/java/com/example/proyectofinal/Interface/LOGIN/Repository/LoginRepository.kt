package com.example.proyectofinal.Interface.LOGIN.Repository

import androidx.compose.runtime.mutableStateListOf
import com.example.proyectofinal.Model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LoginRepository(private val db: FirebaseFirestore =  FirebaseFirestore.getInstance()) {

    suspend fun getUsers(): MutableList<String>{

        val query = db.collection("Users").get().await()
        val list: MutableList<String> = mutableStateListOf<String>()
        for (item in query){
            list.add(item.getString("username")!!)
        }
        return list
    }

    suspend fun getPasswords(): MutableList<String>{

        val query = db.collection("Users").get().await()
        val list: MutableList<String> = mutableStateListOf<String>()
        for (item in query){
            list.add(item.getString("password")!!)
        }
        return list
    }

    suspend fun addUser(user: User){
        db.collection("Users").document(user.username).set(user).await()
    }


}