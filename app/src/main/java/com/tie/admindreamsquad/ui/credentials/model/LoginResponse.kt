package com.tie.admindreamsquad.ui.credentials.model

data class LoginResponse(
    val status: String,
    val message: String,
    val user: User?
)

data class User(
    val id: String,
    val name: String,
    val emp_id: String,
    val mobile_no: String
)
