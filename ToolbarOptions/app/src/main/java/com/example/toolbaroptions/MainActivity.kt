package com.example.toolbaroptions

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.example_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this, "Icon clicked", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.item2 -> {
                Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show()
                return true

            }

            R.id.item3 -> {
                Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                return true

            }

            R.id.sub_item1 -> {
                Toast.makeText(this, "Sub Item 1 clicked", Toast.LENGTH_SHORT).show()
                return true

            }

            R.id.sub_item2 -> {
                Toast.makeText(this, "Sub Item 2 clicked", Toast.LENGTH_SHORT).show()
                return true

            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}