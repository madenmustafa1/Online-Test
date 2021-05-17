package com.maden.testapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maden.testapp.R
import kotlinx.android.synthetic.main.fragment_sign_in.*


class SignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        signUpText.setOnClickListener { intentSignUp() }
        signInButton.setOnClickListener { signIn(view) }

    }

    fun intentSignUp(){
        val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
        Navigation.findNavController(requireActivity(), R.id.loginContainer).navigate(action)
    }

    fun signIn(view: View){

        val email = emailTextSignIn.text.toString()
        val password = passwordTextSignIn.text.toString()

        if (email != null && password != null) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(context, "Hoşgeldiniz.", Toast.LENGTH_LONG).show()
                        intentSignIn()

                    } else {
                        Toast.makeText(context, it.exception?.localizedMessage.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun intentSignIn(){
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        activity?.finish()
    }

}