package com.example.user.users.model.data
data class ApiResponse(
    val data: List<Data>  // Wrap the list in an object
)
data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: String,
    val last_name: String
)