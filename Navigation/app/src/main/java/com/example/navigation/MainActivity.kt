package com.example.navigation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var goToNextButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        goToNextButton = findViewById(R.id.button)

        goToNextButton.setOnClickListener {
            Intent(this@MainActivity, SecondActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}