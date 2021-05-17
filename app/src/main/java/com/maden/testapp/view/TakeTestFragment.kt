package com.maden.testapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maden.testapp.R
import com.maden.testapp.viewmodel.TakeTest
import com.maden.testapp.viewmodel.TestQuesControl
import kotlinx.android.synthetic.main.fragment_take_test.*


class TakeTestFragment : Fragment() {

    lateinit var quesControl : TestQuesControl
    lateinit var takeTest: TakeTest

    var questionNumber: Int = 0
    var answerNumber: Int = 1

    var testName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_take_test, container, false)
    }

    var testType : String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            testName = TakeTestFragmentArgs.fromBundle(it).testName
            testType = TakeTestFragmentArgs.fromBundle(it).testType
        }

        quesControl = TestQuesControl(view)
        quesControl.checkControl()

        takeTest = TakeTest(view)
        takeTest.getQuestion(questionNumber, testName!!, testType!!)

        nextButton.setOnClickListener { nextBtn(it) }
    }

    fun nextBtn(view : View){

        if (quesControl.newQues == true){
            if (questionNumber == 9  && answerNumber == 10){
                questionNumber++
                nextButton.text = "Finish"
            }

             if(questionNumber < 9 && answerNumber < 10){
                questionNumber++
                answerNumber++
                takeTest.getQuestion(questionNumber, testName!!, testType!!)
                takeTest.getAnswer(answerNumber, testName!!, testType!!)
                 if (questionNumber == 9){
                     nextButton.text = "Finish"
                 }
            }

            quesControl.result()
            quesControl.newQues = false
        }
        if (questionNumber == 10){
            quesControl.finish(testName!!, testType!!)
        }
    }
}