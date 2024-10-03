package com.example.user.users.model.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.user.login.model.api.Constant
import com.example.user.users.model.Interface.User_Interface
import com.example.user.users.model.data.ApiResponse
import com.example.user.users.model.data.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {

    fun getUserData(): Call<ApiResponse> {
        return RetrofitInstance.api.getuserData()
    }
}
object RetrofitInstance {


    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Create API interface instance
    val api: User_Interface by lazy {
        retrofit.create(User_Interface::class.java)
    }
}