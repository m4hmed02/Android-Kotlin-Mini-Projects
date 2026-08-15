package com.example.navigationdrawerfragment

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.navigationdrawerfragment.fragments.ChatFragment
import com.example.navigationdrawerfragment.fragments.MessageFragment
import com.example.navigationdrawerfragment.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            MessageFragment()).commit()
        navigationView.setCheckedItem(R.id.nav_message)


        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId) {
                    R.id.nav_message -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                            MessageFragment()).commit()
                    }
                    R.id.nav_chat -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                            ChatFragment()).commit()
                    }
                    R.id.nav_profile -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                            ProfileFragment()).commit()
                    }
                    R.id.nav_send -> {
                        Toast.makeText(this@MainActivity, "Send Clicked", Toast.LENGTH_SHORT).show()
                    }
                    R.id.nav_share -> {
                        Toast.makeText(this@MainActivity, "Share Clicked", Toast.LENGTH_SHORT).show()
                    }
                }
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

        })

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START)
                }else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        val toggle = ActionBarDrawerToggle(this@MainActivity, drawer,
            toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

}