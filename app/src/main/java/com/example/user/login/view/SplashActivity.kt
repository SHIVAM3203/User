package com.example.user.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.user.R
import com.example.user.users.view.UserActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    // Method to check login status
    private fun checkLoginStatus() {
        val sharedPreferences = getSharedPreferences("login_shared_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        Log.d("SplashActivity", "isLoggedIn: ........................................$isLoggedIn")



        if (isLoggedIn) {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Finish the splash screen activity
        finish()
    }
    }
