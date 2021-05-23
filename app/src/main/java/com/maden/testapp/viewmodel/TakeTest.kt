package com.maden.testapp.viewmodel

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_take_test.view.*

class TakeTest(val view: View) {

    private var db = Firebase.firestore
    private var auth = Firebase.auth

    var questionList: List<String> = mutableListOf()
    var answerList: List<String> = mutableListOf()

    fun getQuestion(questionNumber: Int, testName: String, testType: String){
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val dbRefQuestions = db.collection(testType)

        dbRefQuestions.whereEqualTo("testName", testName).get().addOnSuccessListener {
            for (document in it){
                questionList = document["questions"] as List<String>

                       
                view.testName.text = document["testName"].toString()
                view.questionsText.text = questionList[questionNumber]

                
                answerList = document["answer_1"] as List<String>

                view.aCheckBox.text = answerList[0]
                view.bCheckBox.text = answerList[1]
                view.cCheckBox.text = answerList[2]
                view.dCheckBox.text = answerList[3]

                view.progressBar.visibility = View.GONE
            }
        }
    }

    val checkControl = CheckControl(view)

    fun getAnswer(answerNumber: Int, testName: String, testType: String) {
        view.progressBar.visibility = View.VISIBLE

        val dbRefQuestions = db.collection(testType)

        dbRefQuestions.whereEqualTo("testName", testName).get().addOnSuccessListener {
            for (document in it) {

                answerList = document["answer_$answerNumber"] as List<String>

                view.aCheckBox.text = answerList[0]
                view.bCheckBox.text = answerList[1]
                view.cCheckBox.text = answerList[2]
                view.dCheckBox.text = answerList[3]

                checkControl.isClear()
                view.progressBar.visibility = View.GONE
            }
        }
    }
}
