package com.example.persistentbottomsheet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var textViewState: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val view: View = findViewById(R.id.bottom_Sheet)
        bottomSheetBehavior= BottomSheetBehavior.from(view)

        textViewState = findViewById(R.id.textViewState)

        val buttonOpen: Button = findViewById(R.id.button_open)
        val buttonCollapse: Button = findViewById(R.id.button_collapse)

        buttonOpen.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            textViewState.text = "Expanded"
        }
        buttonCollapse.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            textViewState.text = "Collapsed"
        }

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        textViewState.text = "Expanded"
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        textViewState.text = "Dragging"
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        textViewState.text = "Collapsed"
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        textViewState.text = "Settling"
                    }
                }
            }

            override fun onSlide(p0: View, p1: Float) {
                TODO("Not yet implemented")
            }

        })


    }
}