package com.example.proyectofinal.Interface.UserRegister

import com.example.proyectofinal.Model.User
import com.google.firebase.firestore.FirebaseFirestore

class UserRegisterRepository(private val db: FirebaseFirestore =  FirebaseFirestore.getInstance()) {
    suspend fun addUser(user: User){
        db.collection("Users").document(user.username).set(user)
    }

    }

}