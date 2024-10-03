package com.example.user.login.model.InterFace

import com.example.user.login.model.data.LoginRequest
import com.example.user.login.model.data.LoginResponce
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponce>
}
