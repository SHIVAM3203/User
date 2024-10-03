package com.example.user.users.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.user.R
import com.example.user.users.model.repo.UserRepository
import com.example.user.users.viewmodel.MyAdapter
import com.example.user.users.viewmodel.UserViewModel
import com.example.user.users.viewmodel.UserViewModelFactory

class UserActivity : AppCompatActivity() {
    private lateinit var viewmodel: UserViewModel
    private lateinit var userAdapter: MyAdapter
    private lateinit var userrecyclerview: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_user2)
        val repository = UserRepository()
        val factory = UserViewModelFactory(repository)
userrecyclerview = findViewById(R.id.Userrecyclerview)
        userrecyclerview.layoutManager = LinearLayoutManager(this)

        viewmodel = ViewModelProvider(this,factory).get(UserViewModel::class.java)

        viewmodel.getData().observe(this, Observer { userData ->
            if (userData != null) {
                userAdapter = MyAdapter(this,userData)
                userrecyclerview.adapter = userAdapter
            }
        })

//        ClickButton.setOnClickListener{
//            viewmodel.fetchUserData()
//        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // for mode change
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)

        val themechangeButton: Button = findViewById(R.id.changebutton3)
        themechangeButton.setOnClickListener {
            changeTheme()
        }
    }
    private fun changeTheme() {
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                // If currently in dark mode, switch to light mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            AppCompatDelegate.MODE_NIGHT_NO -> {
                // If currently in light mode, switch to dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                // If system default, switch to dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}
