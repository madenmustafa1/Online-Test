package com.maden.testapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.maden.testapp.R
import com.maden.testapp.adapter.AllTestsListAdapter
import com.maden.testapp.viewmodel.AllTestList
import kotlinx.android.synthetic.main.fragment_tests_list.*


class AllTestsListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tests_list, container, false)
    }

    val testList = AllTestList()
    lateinit var testListAdapter: AllTestsListAdapter

    lateinit var testType: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            testType = AllTestsListFragmentArgs.fromBundle(it).testListName
        }

        testList.selectTest(testType)
        testListAdapter = testList.testsAdapter


        //testListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        testListRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        testListRecyclerView.adapter = testListAdapter

    }
}