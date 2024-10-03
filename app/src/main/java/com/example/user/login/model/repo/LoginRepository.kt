package com.example.user.login.model.repo

import android.util.Log
import com.example.user.login.model.InterFace.LoginApi
import com.example.user.login.model.api.Constant
import com.example.user.login.model.data.LoginRequest
import com.example.user.login.model.data.LoginResponce
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginRepository {
    private val loginApi = RetrofitClient.loginApi

    suspend fun login(email: String, password: String): Result<LoginResponce> {
        return try {
            val response = loginApi.login(LoginRequest(email, password))
            Log.d("LoginViewModeel", "Attempting login for email: $email")

            if (response.isSuccessful) {
                Log.d("LoginRepository", "successful")

                Result.success(response.body()!!)
            } else {
                Log.e("LoginRepository", "Login failed: ${response.errorBody()?.string()}")

                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Log.e("LoginRepository", "Login exception", e)

            Result.failure(e)
        }
    }
}

object RetrofitClient {


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val loginApi: LoginApi = retrofit.create(LoginApi::class.java)
}
