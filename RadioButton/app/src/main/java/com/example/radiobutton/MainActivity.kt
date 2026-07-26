package com.example.radiobutton

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

     private lateinit var radioGroup: RadioGroup
     private lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            radioButton = findViewById(id)

            when(radioButton.id) {
                R.id.yesRadioButton -> {
                    Toast.makeText(this@MainActivity, "Yes button Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.noRadioButton -> {
                    Toast.makeText(this@MainActivity, "No button Clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.maybeRadioButton -> {
                    Toast.makeText(this@MainActivity, "Maybe Button Clicked", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}