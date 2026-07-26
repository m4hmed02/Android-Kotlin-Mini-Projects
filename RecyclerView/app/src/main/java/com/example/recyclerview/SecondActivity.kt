package com.example.recyclerview

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerview.Model.Constants

class SecondActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        titleTextView = findViewById(R.id.secondTitle)
        descriptionTextView = findViewById(R.id.secondDescription)

        val data = intent.extras

        data?.let {
            titleTextView.text = data.getString(Constants.KEY_TITLE)
            descriptionTextView.text = data.getString(Constants.KEY_DESCRIPTION)
        }

    }
}