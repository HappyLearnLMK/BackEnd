package com.backend.member.dto.request

import com.backend.member.domain.MemberType
import java.time.LocalDate

class MemberSaveRequest(
    val memberName:String,
    val birthDate: LocalDate,
    val mobile: String,
    val signUpDate: LocalDate,
    val gender: Char,
    val type: MemberType,
    val memberId: String,
    val password: String
)