package com.maden.testapp.viewmodel

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maden.testapp.adapter.AllTestsListAdapter


class AllTestList {

    private var db = Firebase.firestore

    private var testsArrayList = ArrayList<String>()
    private var testsCoverPhoto : String? = null

    lateinit var testsAdapter: AllTestsListAdapter

    var tlist = listOf<String>()

    fun selectTest(testType: String){
        testsArrayList.clear()

        //Storage field url
        testsCoverPhoto = testType
        testsAdapter = AllTestsListAdapter(testsArrayList, testsCoverPhoto!!)

        val dbRefQuestions = db.collection("Test_Type")
        dbRefQuestions.whereEqualTo("testType", testType).get().addOnSuccessListener {
            for (document in it){

                tlist = document["testName"] as List<String>
                /*
                var str = document["testName"].toString()
                var arr = str.dropWhile { !it.isLetter() }
                arr = arr.dropLastWhile { !it.isLetter() }
                arr = arr.filter { !it.isWhitespace() }
                tlist = arr.split(",")
                 */

                for (i in tlist){
                    testsArrayList.add(i)
                }
            }

            testsAdapter.notifyDataSetChanged()
        }
    }
}
