package com.backend.config.security.auth

data class LoginReqDto(
    var username: String,
    var password: String
)
data class LoginResDto(
    val username: String,
    val id: String
)
data class LoginVerifyDto(
    val username: String,
    val id: String,
    val role: String
)