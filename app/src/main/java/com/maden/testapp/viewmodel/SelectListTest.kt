package com.maden.testapp.viewmodel

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maden.testapp.adapter.SelectTestAdapter
import kotlinx.android.synthetic.main.fragment_test_select.view.*

class SelectListTest(val view: View) {

    private var db = Firebase.firestore
    private var auth = Firebase.auth

    private var titleArray = ArrayList<String>()
    var descriptionArray= ArrayList<String>()
    private var imageLink = ArrayList<String>()



    lateinit var testAdapter: SelectTestAdapter


    fun selectTest(testName: String, testCover: String){
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        titleArray.clear()
        descriptionArray.clear()

        testAdapter = SelectTestAdapter(titleArray, descriptionArray, testName, imageLink)

        val dbRefQuestions = db.collection(testName)
        dbRefQuestions.get().addOnSuccessListener {
            for (document in it){
                titleArray.add(document["testName"].toString())
                descriptionArray.add("" + document["testOwner"].toString())
                imageLink.add(document["testImageLink"].toString())

            }
            view.progresbarTestSelect.visibility = View.GONE
            testAdapter.notifyDataSetChanged()
        }
    }
}