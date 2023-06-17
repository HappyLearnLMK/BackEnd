package com.backend.user.domain

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "MEMBER")
class User(
    var userName:String,
    var birthDate: LocalDate,
    var mobile: String,
    val signUpDate: LocalDate,
    var gender: Char,
    @Enumerated(EnumType.STRING)
    var type: UserType,
    val userId:String,
    var password:String,
    @Id
    val userCode:String?
) {

}