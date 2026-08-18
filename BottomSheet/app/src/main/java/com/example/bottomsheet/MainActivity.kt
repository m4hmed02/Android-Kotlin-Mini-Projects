package com.example.bottomsheet

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bottomsheet.dialogs.MyBottomSheetDialog

class MainActivity : AppCompatActivity(), MyBottomSheetDialog.BottomSheetListener {

    private lateinit var openDialog: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        openDialog = findViewById(R.id.button_open_dialog)
        textView = findViewById(R.id.textview)

        openDialog.setOnClickListener {
            val myBottomSheetDialog = MyBottomSheetDialog()
            myBottomSheetDialog.show(supportFragmentManager, "Dialog")
        }

    }

    override fun onButtonClicked(input: String) {
        textView.text = input
    }
}