package com.backend.user.dto.request

import com.backend.user.domain.UserType
import java.time.LocalDate

data class UserUpdateRequest(
    val userCode: String,
    val userName:String,
    val mobile: String,
    val gender: Char,
    val type: UserType
)