package com.maden.testapp.viewmodel

import android.view.View
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maden.testapp.R
import com.maden.testapp.view.GLOBAL_ACTIVITY
import com.maden.testapp.view.TakeTestFragmentDirections
import kotlinx.android.synthetic.main.fragment_take_test.view.*

class TestQuesControl(val view: View) {

    var positive : Int = 0
    var negative : Int = 0
    var selection : Int = 0

    var newQues: Boolean? = null
    var answerList: List<String> = mutableListOf()

    private var db = Firebase.firestore
    private var auth = Firebase.auth

    val checkControl = CheckControl(view)


    fun checkControl(){
        view.aCheckBox.setOnClickListener { aIsChecked() }
        view.bCheckBox.setOnClickListener { bIsChecked() }
        view.cCheckBox.setOnClickListener { cIsChecked() }
        view.dCheckBox.setOnClickListener { dIsChecked() }
    }

    fun aIsChecked(){
        checkControl.aCheck()
        newQuesControl() }
    fun bIsChecked(){
        checkControl.bCheck()
        newQuesControl() }
    fun cIsChecked(){
        checkControl.cCheck()
        newQuesControl() }
    fun dIsChecked(){
        checkControl.dCheck()
        newQuesControl() }

    fun newQuesControl(){
        newQues = view.aCheckBox.isChecked || view.bCheckBox.isChecked ||
                view.cCheckBox.isChecked || view.dCheckBox.isChecked }

    fun result(){
        selection++
        if (selection < 11){
            when {
                view.aCheckBox.isChecked -> { positive += 10 }
                view.bCheckBox.isChecked -> { positive += 5 }
                view.cCheckBox.isChecked -> { negative -= 5 }
                view.dCheckBox.isChecked -> { negative -= 10 }
            }
        }
    }

    fun finish(testName: String, testType: String){
        view.progressBar.visibility = View.VISIBLE
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        var result = positive + negative
        var resultFireBase: Int = 0

        val dbRefQuestions = db.collection(testType)
        dbRefQuestions.whereEqualTo("testName", testName).get().addOnSuccessListener {
            for (document in it){
                when {
                    result in 70..100 -> { resultFireBase = 0 }
                    result in 51..69 -> { resultFireBase = 1 }
                    result in 25..49 -> { resultFireBase = 2 }
                    result < 25 -> { resultFireBase = 3 }
                }
                /*
                var strA = document["result"].toString()
                var arrA = strA.dropWhile { !it.isLetter() }
                arrA = arrA.dropLastWhile { !it.isLetter() }
                 */
                answerList = document["result"] as List<String>

                println(answerList[resultFireBase])

                view.progressBar.visibility = View.GONE


                val action = TakeTestFragmentDirections.actionTakeTestFragmentToResultFragment(answerList[resultFireBase])
                Navigation.findNavController(GLOBAL_ACTIVITY!!, R.id.main_layout).navigate(action)

            }
        }
    }
}