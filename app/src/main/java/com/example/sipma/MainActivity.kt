package com.example.sipma

import HomeFragment
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sipma.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val homeFragment: Fragment = HomeFragment()
    private val profileFragment: Fragment = ProfileFragment()

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    loadFragment(profileFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        loadFragment(homeFragment)
        FirebaseMessaging.getInstance().subscribeToTopic("pengumuman")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Jika langganan berhasil, tampilkan toast
                    showToast("Berhasil subscribe ke topik pengumuman")
                } else {
                    // Jika langganan gagal, tampilkan toast dengan pesan kesalahan
                    val errorMessage = task.exception?.message ?: "Unknown error"
                    showToast("Gagal subscribe ke topik pengumuman. Error: $errorMessage")
                }
            }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }
}
