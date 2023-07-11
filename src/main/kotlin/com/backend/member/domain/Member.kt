package com.backend.member.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "MEMBER")
class Member(
    var memberName:String,
    val birthDate: LocalDate,
    var mobile: String,
    val signUpDate: LocalDate,
    var gender: Char,
    @Enumerated(EnumType.STRING)
    var type: MemberType,
    @field:Email
    val memberId:String,
    val password:String,
    @Id
    val memberCode:String?
) {

}