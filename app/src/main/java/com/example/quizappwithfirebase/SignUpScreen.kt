package com.example.quizappwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizappwithfirebase.databinding.ActivitySignUpScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpScreen : AppCompatActivity() {
    lateinit var binding: ActivitySignUpScreenBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            signUpUser()
        }

        binding.tvLogin.setOnClickListener {

            val intent = Intent(this,LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpUser(){

        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this, "all fields  must be filled", Toast.LENGTH_SHORT).show()
            return
        }
        if (password!=confirmPassword){
            Toast.makeText(this,"password and confirmPassword do not match", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful) {

                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this,"Error creating user", Toast.LENGTH_SHORT).show()
                }
            }
    }

}