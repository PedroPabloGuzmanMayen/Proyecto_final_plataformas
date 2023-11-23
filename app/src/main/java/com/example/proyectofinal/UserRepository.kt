package com.example.proyectofinal

import androidx.compose.runtime.mutableStateListOf
import com.example.proyectofinal.Model.TaskModel
import com.example.proyectofinal.Model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class UserRepository(private val db: FirebaseFirestore =  FirebaseFirestore.getInstance()) {

    suspend fun getUserData(username: String): User? {
        return try {
            val query = db.collection("Users").document(username).get().await()

            if (query.exists()) {
                val user: User = User(
                    name = query.getString("name")!!,
                    password = query.getString("password")!!
                )
                user
            } else {
                null
            }

        } catch (e: Exception) {
            // Handle the exception if needed
            null
        }
    }


    suspend fun getUserLists(username: String): MutableList<String>{
        val query = db.collection("Users").document(username).collection("Lists").get().await()
        val list: MutableList<String> = mutableStateListOf<String>()
        for (item in query){
            list.add(item.getString("name")!!)
        }
        return list

    }

    suspend fun getUserActivities(username: String, listName: String): MutableList<TaskModel>{
        val query = db.collection("Users").document(username).collection("Lists").document(listName).collection("Activities").get().await()
        val list: MutableList<TaskModel> = mutableStateListOf<TaskModel>()
        for (document in query){
            list.add(
                TaskModel(
                    name = document.getString("name")!!,
                    date = document.getString("date")!!,
                    time = document.getString("time")!!
                )
            )
        }
        return list
    }

    suspend fun createUser(username: String, password: String){
        val user = hashMapOf(
            "name" to username,
            "password" to password
        )
        db.collection("Users").document(username).set(user).await()
    }

    suspend fun addList(username: String, listName: String){
        val list = hashMapOf(
            "name" to listName
        )
        db.collection("Users").document(username).collection("Lists").document(listName).set(list).await()
    }

    suspend fun addActivity(username: String, listName: String, activity: TaskModel){

        db.collection("Users").document(username).collection("Lists").document(listName).collection("Activities").document(activity.name).set(activity).await()
    }
}
