package com.maden.testapp.viewmodel

import android.content.Intent
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maden.testapp.view.MainActivity
import kotlinx.android.synthetic.main.activity_main.view.*

class AuthControl() {
    private var db = Firebase.firestore
    private var auth = Firebase.auth
    var authType: String? = null

    fun authControl(){
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val dbRefQuestions = db.collection("Profile")
        dbRefQuestions.whereEqualTo("email", auth.currentUser.email).get().addOnSuccessListener {
            for (document in it){
                authType = document["user"].toString()
            }
        }
    }
}