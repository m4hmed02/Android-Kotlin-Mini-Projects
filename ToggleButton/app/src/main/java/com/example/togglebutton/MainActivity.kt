package com.example.togglebutton

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var toggleButton: ToggleButton
    private lateinit var toggleText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toggleButton = findViewById(R.id.toggleButton)
        toggleText = findViewById(R.id.toggleText)
        
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                toggleText.visibility = View.VISIBLE
            } else{
                toggleText.visibility = View.INVISIBLE
            }

        }


    }
}