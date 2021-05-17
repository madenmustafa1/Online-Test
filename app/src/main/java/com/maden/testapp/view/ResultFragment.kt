package com.maden.testapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.maden.testapp.R
import kotlinx.android.synthetic.main.fragment_result.*


class ResultFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    var result: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            result = ResultFragmentArgs.fromBundle(it).result
            resultTextView.setText(result)
        }

        homeButton.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToTestTypeFragment()
            Navigation.findNavController(GLOBAL_ACTIVITY!!, R.id.main_layout).navigate(action)
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Handle the back button event
            val action = ResultFragmentDirections.actionResultFragmentToTestTypeFragment()
            Navigation.findNavController(GLOBAL_ACTIVITY!!, R.id.main_layout).navigate(action)
        }
    }

}