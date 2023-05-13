package com.backend.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate
import java.util.*

@Entity
class User(
    val userName:String,
    val birthDate: LocalDate,
    val mobile: String,
    val signUpDate: LocalDate,
    val gender: Char,
    val type: UserType,
    @Id
    val userCode:String?
) {

}