package com.example.quizappwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val startedButton = findViewById<TextView>(R.id.btn_GetStarted)

        startedButton.setOnClickListener {
            val intent = Intent(this,LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}