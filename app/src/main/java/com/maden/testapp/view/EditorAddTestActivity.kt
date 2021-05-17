package com.maden.testapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maden.testapp.R
import kotlinx.android.synthetic.main.activity_editor_add_test.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class EditorAddTestActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore

    var dropListItem = ArrayList<String>()
    var tlist = listOf<String>()
    var questionsList = ArrayList<String>()
    var answerList = ArrayList<String>()

    var collectionPath: String? = null
    var documentPath: String? = null
    var uniqueID = UUID.randomUUID().toString()
    var questCtrl = 0
    var userNameSurName: String? = null

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor_add_test)


        testType()

        spinnerAddTest.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                println(dropListItem[position])
                collectionPath = dropListItem[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }
        }
    }


    fun confirmQuestButton(view: View){

        if (questCtrl == 11){ questCtrl++ }

        if(questCtrl < 10){
            ques()
        } else if (questCtrl == 10) {

            questCtrl++
            var quesData = mapOf("questions" to questionsList)
            dbRef(collectionPath!!, uniqueID, quesData = quesData)


            quesEditText.visibility = View.GONE
            editorTestTitleEditText.visibility = View.GONE
            spinnerAddTest.visibility = View.GONE

            questCtrl++

        } else if (questCtrl == 12){
            val result1 = firstAnswerEditText.text.toString()
            val result2 = secondAnswerEditText.text.toString()
            val result3 = thirdAnswerEditText.text.toString()
            val result4 = forthAnswerEditText.text.toString()
            val resultList = ArrayList<String>()
            resultList.add(result1)
            resultList.add(result2)
            resultList.add(result3)
            resultList.add(result4)


            val dbAuthName = db.collection("Profile")
            dbAuthName.whereEqualTo("email", auth.currentUser.email)
                    .get().addOnSuccessListener {
                for (document in it){
                    userNameSurName = document["name"].toString() + " " + document["surname"]
                }
            }.addOnCompleteListener {

                        documentPath = editorTestTitleEditText.text.toString()
                        var resultData = mapOf(
                                "result" to resultList,
                                "testName" to documentPath,
                                "type" to collectionPath,
                                "UUID" to uniqueID,
                                "testOwner" to userNameSurName)

                        dbRef(collectionPath!!, uniqueID, quesData = resultData)

                        val intent = Intent(applicationContext, EditorActivity::class.java)
                        startActivity(intent)
                        finish()
            }
        }
    }


    fun testType(){
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        val dbRefQuestions = db.collection("Test_Type")
        dbRefQuestions.whereEqualTo("testType","Kişilik Testleri").get().addOnSuccessListener {
            for (document in it){
                tlist = document["testName"] as List<String>

                for (i in tlist){ dropListItem.add(i) }
            }
        }.addOnCompleteListener {
            var adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dropListItem);
            spinnerAddTest.adapter = adapter
        }
    }

    fun dbRef(collectionPath: String, uniqueID: String, quesData: Map<String, Serializable?>){
        db.collection(collectionPath!!).document(uniqueID)
                .update(quesData)
                .addOnSuccessListener { println("Success") }
                .addOnFailureListener { e -> println(e) }
    }

    fun ques(){

        val answer1 = firstAnswerEditText.text.toString()
        val answer2 = secondAnswerEditText.text.toString()
        val answer3 = thirdAnswerEditText.text.toString()
        val answer4 = forthAnswerEditText.text.toString()

        if(answer1 != null && answer2 != null && answer3 != null && answer4 != null){
            questCtrl++
            questionsList.add(quesEditText.text.toString())
            answerList.add(answer1)
            answerList.add(answer2)
            answerList.add(answer3)
            answerList.add(answer4)

            if (questCtrl == 1){
                var answerData = hashMapOf(
                        "answer_${questCtrl}" to answerList
                )
                documentPath = editorTestTitleEditText.text.toString()

                db.collection(collectionPath!!).document(uniqueID)
                        .set(answerData)
                        .addOnSuccessListener { println("Success") }
                        .addOnFailureListener { e -> println(e) }
                answerList.clear()
            } else {
                var answerData = mapOf(
                        "answer_${questCtrl}" to answerList
                )
                documentPath = editorTestTitleEditText.text.toString()
                db.collection(collectionPath!!).document(uniqueID)
                        .update(answerData)
                        .addOnSuccessListener { println("Success") }
                        .addOnFailureListener { e -> println(e) }
                answerList.clear()
            }

            if (questCtrl == 10){
                confirmQuestButtonEditor.text = "Bitir"
            }
        }
    }
}
