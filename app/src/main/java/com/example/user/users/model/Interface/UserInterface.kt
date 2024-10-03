package com.example.user.users.model.Interface

import com.example.user.users.model.data.ApiResponse
import com.example.user.users.model.data.Data
import retrofit2.Call
import retrofit2.http.GET

interface User_Interface {
    @GET("users?page=2")
    fun getuserData(): Call<ApiResponse>
}
