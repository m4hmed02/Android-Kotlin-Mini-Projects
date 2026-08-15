package com.example.fragmentscommunication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fragmentscommunication.fragments.FragmentA
import com.example.fragmentscommunication.fragments.FragmentB

class MainActivity : AppCompatActivity(), FragmentA.FragmentAListener, FragmentB.FragmentBListener {
    private lateinit var fragmentA: FragmentA
    private lateinit var fragmentB: FragmentB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        fragmentA = FragmentA()
        fragmentB = FragmentB()

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerA, fragmentA)
            .replace(R.id.containerB, fragmentB)
            .commit()
    }

    override fun sendFromA(input: String) {
        fragmentB.updateText(input)
    }

    override fun sendFromB(input: String) {
        fragmentA.updateText(input)
    }
}