package com.backend.user.dto.request

import com.backend.user.domain.UserType
import java.time.LocalDate

data class UserSaveRequest(
    val userName:String,
    val birthDate: LocalDate,
    val mobile: String,
    val signUpDate: LocalDate,
    val gender: Char,
    val type: UserType
)