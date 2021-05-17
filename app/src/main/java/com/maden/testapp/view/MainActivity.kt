package com.maden.testapp.view

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.maden.testapp.R
import com.maden.testapp.viewmodel.AuthControl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

var GLOBAL_ACTIVITY: Activity? =null

class MainActivity : AppCompatActivity() {

    private var auth = Firebase.auth
    val authControl = AuthControl()

    val authS: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        GLOBAL_ACTIVITY = this

        GlobalScope.async { authControl() }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.sign_out){
            Firebase.auth.signOut()
            val intent = Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private suspend fun authControl(){
        authControl.authControl()
        delay(2000)
        if(authControl.authType == "admin" && auth.currentUser != null){
            val intent  = Intent(applicationContext, AdminActivity::class.java)
            startActivity(intent)
        }else if (authControl.authType == "editor" && auth.currentUser != null){
            val intent = Intent(applicationContext, EditorActivity::class.java)
            startActivity(intent)
        }
    }
}