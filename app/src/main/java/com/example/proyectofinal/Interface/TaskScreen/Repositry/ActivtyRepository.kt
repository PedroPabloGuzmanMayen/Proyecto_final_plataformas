package com.example.proyectofinal.Interface.TaskScreen.Repositry

import androidx.compose.runtime.mutableStateListOf
import com.example.proyectofinal.Model.TaskModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ActivtyRepository(private val db: FirebaseFirestore =  FirebaseFirestore.getInstance()) {
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
    suspend fun addActivity(username: String, listName: String, activity: TaskModel){

        db.collection("Users").document(username).collection("Lists").document(listName).collection("Activities").document(activity.name).set(activity).await()
    }

    suspend fun deleteActivity(username: String, listname: String, activity:String){
        db.collection("Users").document(username).collection("Lists").document(listname).collection("Activities").document(activity).delete().await()
    }
}