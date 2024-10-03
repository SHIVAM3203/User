package com.example.user.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.user.R
import com.example.user.users.view.UserActivity
import com.example.user.login.model.repo.LoginRepository
import com.example.user.login.viewmodel.LoginViewModel
import com.example.user.login.viewmodel.LoginViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        emailEditText = findViewById(R.id.emailtext)
        passwordEditText = findViewById(R.id.Passwordtext)
        loginButton = findViewById(R.id.btView8)
        val factory = LoginViewModelFactory(LoginRepository())

        viewModel = ViewModelProvider(this, factory ).get(LoginViewModel::class.java)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.login(email, password)
        }

        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { loginResponse ->
                saveLoginStatus()
                Log.d("MainActivity", "........................................Login status saved: isLoggedIn = true")

                startActivity(intent)
                Log.d("LoginActivity", ".........................Login successful: $loginResponse")
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
                finish()
            }.onFailure { error ->
                Log.e("LoginActivity", "......................Login failed", error)
                Toast.makeText(this, "Login failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun saveLoginStatus() {
        val sharedPreferences = getSharedPreferences("login_shared_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }
}