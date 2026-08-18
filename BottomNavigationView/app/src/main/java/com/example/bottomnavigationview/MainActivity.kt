package com.example.bottomnavigationview

import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bottomnavigationview.fragments.FavouritesFragment
import com.example.bottomnavigationview.fragments.HomeFragment
import com.example.bottomnavigationview.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()

        bottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnItemSelectedListener {
            var fragment: Fragment? = null
            when(it.itemId) {
                R.id.nav_home -> {
                    fragment = HomeFragment()
                }
                R.id.nav_favourites -> {
                    fragment = FavouritesFragment()
                }
                R.id.nav_search -> {
                    fragment = SearchFragment()
                }
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment!!)
                .commit()

            return@setOnItemSelectedListener true
        }
    }
}