package com.maden.testapp.viewmodel

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maden.testapp.adapter.AllTypeAdapter

class
AllSelectTypeList() {

    private var db = Firebase.firestore

    private var typeArrayList = ArrayList<String>()
    lateinit var typeAdapter : AllTypeAdapter

    fun selectType(){
        typeArrayList.clear()
        typeAdapter = AllTypeAdapter(typeArrayList)

        val dbRefQuestions = db.collection("Test_Type")
        dbRefQuestions.get().addOnSuccessListener {
            for (document in it){

                typeArrayList.add(document["testType"].toString())
            }
            typeAdapter.notifyDataSetChanged()
        }
    }
}