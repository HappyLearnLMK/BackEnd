package com.backend.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.time.LocalDate
import java.util.*

@Entity
class User(
    var userName:String,
    var birthDate: LocalDate,
    var mobile: String,
    val signUpDate: LocalDate,
    var gender: Char,
    @Enumerated(EnumType.STRING)
    var type: UserType,
    @Id
    val userCode:String?
) {

}