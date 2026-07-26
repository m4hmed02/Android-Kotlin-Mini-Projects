package com.example.datafromfirsttosecondactivity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    private lateinit var textData: TextView
    private lateinit var goBackButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textData = findViewById(R.id.SecondScreenTextView)

        val data = intent.extras

        data?.let {
            val message1 = it.getString(Constants.INTENT_MESSAGE_KEY)
            val message2 = it.getString(Constants.INTENT_MESSAGE2_KEY)
            val message3 = it.getDouble(Constants.INTENT_NUMBER_KEY)

            textData.text = message1 + "\n" + message2 + "\n" + message3
        }

        goBackButton = findViewById(R.id.goBackButton)

        goBackButton.setOnClickListener {
            val intent = intent
            intent.putExtra(Constants.INTENT_MESSAGE2_KEY, "Message From Second Screen")
            setResult(Constants.RESULT_CODE, intent)
            finish()
        }



    }
}