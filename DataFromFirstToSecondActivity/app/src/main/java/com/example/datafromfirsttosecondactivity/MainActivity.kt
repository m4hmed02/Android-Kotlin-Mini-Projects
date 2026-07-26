package com.example.datafromfirsttosecondactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var goToNextButton : Button
    private lateinit var firstTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        firstTextView = findViewById(R.id.textView)

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Constants.RESULT_CODE){
                val message = it.data!!.getStringExtra(Constants.INTENT_MESSAGE2_KEY)
                firstTextView.text = message
            }
        }

        goToNextButton = findViewById(R.id.goToButton)

        goToNextButton.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra(Constants.INTENT_MESSAGE_KEY, "Hello From First Activity")
            intent.putExtra(Constants.INTENT_MESSAGE2_KEY, "This is Message 2 from First Activity")
            intent.putExtra(Constants.INTENT_NUMBER_KEY, 9.15315)

            getResult.launch(intent)
//            startActivity(intent)
        }

    }
}