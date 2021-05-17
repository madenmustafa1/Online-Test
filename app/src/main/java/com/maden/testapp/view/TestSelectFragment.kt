package com.maden.testapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.maden.testapp.R
import com.maden.testapp.adapter.SelectTestAdapter
import com.maden.testapp.viewmodel.SelectListTest
import kotlinx.android.synthetic.main.fragment_test_select.*


class TestSelectFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_select, container, false)
    }


    lateinit var selectTest : SelectListTest
    lateinit var testAdapter: SelectTestAdapter


    lateinit var testType : String
    lateinit var testCover : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            testType = TestSelectFragmentArgs.fromBundle(it).testName
            testCover = TestSelectFragmentArgs.fromBundle(it).testCover
        }


        selectTest = SelectListTest(view)
        selectTest.selectTest(testType, testCover)
        testAdapter = selectTest.testAdapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = testAdapter



        //testAdapter.notifyDataSetChanged()
    }
}