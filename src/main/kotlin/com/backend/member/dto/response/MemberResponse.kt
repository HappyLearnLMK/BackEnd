package com.backend.member.dto.response

import java.time.LocalDate

class MemberResponse(
    val memberCode: String,
    val memberName:String,
    val birthDate: LocalDate,
    val mobile: String,
    val signUpDate: LocalDate,
    val gender: Char,
    val type: Char
) {
}