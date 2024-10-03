package com.example.user.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.user.login.model.data.LoginResponce
import com.example.user.login.model.repo.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Result<LoginResponce>>()
    val loginResult: LiveData<Result<LoginResponce>> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            Log.d("LoginViewModel", "Attempting login for email: $email")
            _loginResult.value = loginRepository.login(email, password)
        }
    }
}
class LoginViewModelFactory(private val loginRepository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(loginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
