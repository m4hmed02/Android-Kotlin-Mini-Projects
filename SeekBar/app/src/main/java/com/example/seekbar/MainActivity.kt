package com.example.seekbar

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private lateinit var resultText: Text

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekBar)
        resultText = findViewById(R.id.resultText)

        seekBar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
            override fun onProgressChanged(
                p0: SeekBar?,
                p1: Int,
                p2: Boolean
            ) {
                Toast.makeText(this@MainActivity, "onProgressChanged", Toast.LENGTH_SHORT).show()
                resultText.textContent = "Result : " + p0!!.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Toast.makeText(this@MainActivity, "onStartTrackingTouch", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Toast.makeText(this@MainActivity, "onStopTrackingTouch", Toast.LENGTH_SHORT).show()
                resultText.textContent = "Final result : " + p0!!.progress.toString()
            }
        })


    }


}