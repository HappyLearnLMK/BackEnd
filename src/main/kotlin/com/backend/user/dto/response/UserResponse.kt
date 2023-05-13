package com.backend.user.dto.response

import java.time.LocalDate

class UserResponse(
    val userCode: String,
    val userName:String,
    val birthDate: LocalDate,
    val mobile: String,
    val signUpDate: LocalDate,
    val gender: Char,
    val type: Char
) {
}