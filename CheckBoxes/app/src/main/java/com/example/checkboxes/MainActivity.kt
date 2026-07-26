package com.example.checkboxes

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var checkBoxKotlin: CheckBox
    private lateinit var checkBoxJava: CheckBox
    private lateinit var checkBoxPython: CheckBox
    private  lateinit var showButton: Button
    private lateinit var textViewStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        checkBoxKotlin = findViewById(R.id.checkBoxKotlin)
        checkBoxJava = findViewById(R.id.checkBoxJava)
        checkBoxPython = findViewById(R.id.checkBoxPython)
        showButton = findViewById(R.id.showButton)
        textViewStatus = findViewById(R.id.textViewStatus)

        showButton.setOnClickListener {
            val sb = StringBuilder()

            sb.append(checkBoxKotlin.text.toString() + " status : " +
                        checkBoxKotlin.isChecked.toString() + "\n")
            sb.append(checkBoxJava.text.toString() + " status : " +
                        checkBoxJava.isChecked.toString() + "\n")
            sb.append(checkBoxPython.text.toString() + " status : " +
                        checkBoxPython.isChecked.toString() + "\n")

            textViewStatus.text = sb.toString()

        }


    }
}