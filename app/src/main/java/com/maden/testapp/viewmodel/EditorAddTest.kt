package com.maden.testapp.viewmodel

import android.view.View
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maden.testapp.view.EditorAddTestActivity
import kotlinx.android.synthetic.main.activity_editor_add_test.*
import kotlinx.android.synthetic.main.activity_editor_add_test.view.*

class EditorAddTest(val view: View) {



    /*
    private var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    var questionsList = ArrayList<String>()
    var answerList = ArrayList<String>()

    var documentPath: String? = null

    fun ques(collectionPath: String, questCtrl1: Int,
             answer1: String, answer2: String,
             answer3: String, answer4: String,
            documentPathh: String){
        db = FirebaseFirestore.getInstance()
        auth = Firebase.auth

        var questCtrl = questCtrl1


        if(answer1 != null && answer2 != null && answer3 != null && answer4 != null){
            questCtrl++
            questionsList.add("view.quesEditText.text.toString()")
            answerList.add(answer1)
            answerList.add(answer2)
            answerList.add(answer3)
            answerList.add(answer4)

            if (questCtrl == 1){
                var answerData = hashMapOf(
                        "answer_${questCtrl}" to answerList
                )
                documentPath = documentPathh

                db.collection(collectionPath!!).document(documentPath!!)
                        .set(answerData)
                        .addOnSuccessListener { println("Success") }
                        .addOnFailureListener { e -> println(e) }
                answerList.clear()
            } else {
                var answerData = mapOf(
                        "answer_${questCtrl}" to answerList
                )
                documentPath = documentPathh
                db.collection(collectionPath!!).document(documentPath!!)
                        .update(answerData)
                        .addOnSuccessListener { println("Success") }
                        .addOnFailureListener { e -> println(e) }
                answerList.clear()
            }



        }
    }
     */

}