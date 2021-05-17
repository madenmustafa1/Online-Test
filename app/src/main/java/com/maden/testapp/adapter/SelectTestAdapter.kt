package com.maden.testapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.maden.testapp.R
import com.maden.testapp.view.*
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.test_select_model.view.*
import kotlinx.android.synthetic.main.tests_adapter_model.view.*
import java.lang.Exception

class SelectTestAdapter(
    private val title: ArrayList<String>,
    private val description: ArrayList<String>,
    private val testType: String,
    private val imageLink: ArrayList<String>
    ): RecyclerView.Adapter<SelectTestAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init { }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context)
        val view = inflateView.inflate(R.layout.test_select_model, parent, false)

        return ViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.titleTextView.text = title[position]
        holder.itemView.descriptionTextView.text = description[position]

        holder.itemView.testSelectImageView.setOnClickListener {
            takeTest(it, title[position])
        }



        Picasso.get()
                .load(imageLink[position])
                .error(R.drawable.ic_launcher_background)
                //.placeholder(R.drawable.ic_launcher_background)
                .into(holder.itemView.testSelectImageView, object : Callback {
                    override fun onSuccess() {
                        holder.itemView.testSelectProgressbar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        holder.itemView.testSelectImageView.setImageResource(R.drawable.ic_launcher_background)
                        holder.itemView.testSelectProgressbar.visibility = View.GONE
                    }
                });

    }

    override fun getItemCount(): Int {
        return title.size
    }

    fun takeTest(view: View, testName: String){

        val action = TestSelectFragmentDirections.actionTestSelectFragmentToTakeTestFragment(testName, testType)
        Navigation.findNavController(GLOBAL_ACTIVITY!!, R.id.main_layout).navigate(action)
    }
}