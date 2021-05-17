package com.maden.testapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.maden.testapp.R
import com.maden.testapp.adapter.AllTypeAdapter
import com.maden.testapp.viewmodel.AllSelectTypeList
import kotlinx.android.synthetic.main.fragment_test_type.*


class AllTestTypeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_type, container, false)
    }

    val selectTypeList = AllSelectTypeList()
    lateinit var typeAdapter: AllTypeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectTypeList.selectType()
        typeAdapter = selectTypeList.typeAdapter

        //typeRecyclerAdapter.layoutManager = LinearLayoutManager(requireContext())
        typeRecyclerAdapter.layoutManager = GridLayoutManager(requireContext(), 3)
        typeRecyclerAdapter.adapter = typeAdapter
    }
}