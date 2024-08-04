package com.example.quizappwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizappwithfirebase.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth

class LoginScreen : AppCompatActivity() {
    lateinit var binding: ActivityLoginScreenBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.tvSignUp.setOnClickListener {

            val intent = Intent(this,SignUpScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser(){
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Email/password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"successfully Login",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,HomeScreen::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Authentication failed",Toast.LENGTH_SHORT).show()
                }
            }
    }
}