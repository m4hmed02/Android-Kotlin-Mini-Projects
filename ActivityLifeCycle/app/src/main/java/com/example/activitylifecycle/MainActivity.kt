package com.example.activitylifecycle

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Toast.makeText(this@MainActivity, "onCreate Called", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this@MainActivity, "onStart Called", Toast.LENGTH_SHORT).show()

    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this@MainActivity, "onResume Called", Toast.LENGTH_SHORT).show()

    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this@MainActivity, "onPause Called", Toast.LENGTH_SHORT).show()

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this@MainActivity, "onStop Called", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this@MainActivity, "onDestroy Called", Toast.LENGTH_SHORT).show()

    }
}