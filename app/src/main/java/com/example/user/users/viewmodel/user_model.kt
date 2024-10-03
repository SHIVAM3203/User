package com.example.user.users.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.user.users.model.data.ApiResponse
import com.example.user.users.model.data.Data
import com.example.user.users.model.repo.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val userData = MutableLiveData<List<Data>>()

init {
    fetchUserData()
}

    fun fetchUserData() {
        repository.getUserData().enqueue(object : Callback<ApiResponse> {  // Update callback type
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                userData.value = response.body()?.data  // Access the list of users inside ApiResponse
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("API Error", t.message.toString())
            }
        })
    }

    // Function to expose the LiveData to be observed in MainActivity
    fun getData(): LiveData<List<Data>> {
        return userData
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
