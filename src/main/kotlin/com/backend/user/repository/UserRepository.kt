package com.backend.user.repository

import com.backend.user.domain.User
import com.backend.user.dto.request.UserUpdateRequest
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun deleteByUserCode(userCode:String):Int
    fun findFirstByOrderByUserCodeDesc():User
    fun findByUserCode(userCode: String): User
}