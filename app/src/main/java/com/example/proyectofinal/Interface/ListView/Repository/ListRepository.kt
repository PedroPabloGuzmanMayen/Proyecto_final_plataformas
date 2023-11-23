package com.example.proyectofinal.Interface.ListView.Repository

import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ListRepository(private val db: FirebaseFirestore =  FirebaseFirestore.getInstance()) {
    suspend fun getUserLists(username: String): MutableList<String>{
        val query = db.collection("Users").document(username).collection("Lists").get().await()
        val list: MutableList<String> = mutableStateListOf<String>()
        for (item in query){
            list.add(item.getString("name")!!)
        }
        return list

    }
    suspend fun addList(username: String, listName: String){
        val list = hashMapOf(
            "name" to listName
        )
        db.collection("Users").document(username).collection("Lists").document(listName).set(list).await()
    }
}