package com.backend.user.dto.request

import com.backend.user.domain.UserType
import java.time.LocalDate

data class UserUpdateRequest(
    var userCode: String,
    var userName:String,
    var mobile: String,
    var gender: Char,
    var type: UserType,
    var password: String
)