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
import com.maden.testapp.view.AllTestsListFragmentDirections
import com.maden.testapp.view.GLOBAL_ACTIVITY
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tests_adapter_model.view.*
import java.lang.Exception

class AllTestsListAdapter(
        private val testsList: ArrayList<String>,
        private val testsCoverPhoto: String
): RecyclerView.Adapter<AllTestsListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context)
        val view = inflateView.inflate(R.layout.tests_adapter_model, parent, false)

        return  AllTestsListAdapter.ViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.testsAdapterTextView.text = testsList[position]

        var storage = Firebase.storage
        var storageRef = storage.reference

        var pathReference: StorageReference? = storageRef
                .child(testsCoverPhoto)
                .child("cover")
                .child(testsList[position] + ".png")
        pathReference?.downloadUrl?.addOnSuccessListener {
            var downloadURL = it

            if (downloadURL != null){
                Picasso.get()
                        .load(downloadURL)
                        .error(R.drawable.ic_launcher_background)
                        //.placeholder(R.drawable.ic_launcher_background)
                        .into(holder.itemView.testsAdapterImageView, object : Callback {
                            override fun onSuccess() {
                                holder.itemView.progressBarAllTestImageView.visibility = View.GONE
                            }

                            override fun onError(e: Exception?) {
                                holder.itemView.testsAdapterImageView.setImageResource(R.drawable.ic_launcher_background)
                            }
                        });
            }
        }?.addOnFailureListener {
            holder.itemView.progressBarAllTestImageView.visibility = View.GONE
            holder.itemView.testsAdapterImageView.setImageResource(R.drawable.ic_launcher_background)
        }



        holder.itemView.testsAdapterImageView.setOnClickListener {
            showTests(testType = testsList[position])
        }


    }

    override fun getItemCount(): Int {
        return testsList.size
    }


    fun showTests(testType: String){
        val action = AllTestsListFragmentDirections.actionTestsListFragmentToTestSelectFragment(testType, testsCoverPhoto)
        Navigation.findNavController(GLOBAL_ACTIVITY!!, R.id.main_layout).navigate(action)
    }

}