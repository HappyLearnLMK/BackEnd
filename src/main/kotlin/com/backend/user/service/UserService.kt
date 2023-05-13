package com.backend.user.service

import com.backend.user.domain.User
import com.backend.user.dto.request.UserSaveRequest
import com.backend.user.dto.request.UserUpdateRequest
import com.backend.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional
    fun saveUser(request: UserSaveRequest) {
        userRepository.save(
                        User(request.userName,
                            request.birthDate,
                            request.mobile,
                            request.signUpDate,
                            request.gender,
                            request.type,
                            "U0002")
        )
    }
    @Transactional
    fun getUsers(): MutableList<User> {
        return userRepository.findAll()
    }
    /*@Transactional
    fun updateUser(request: UserUpdateRequest) {
        return userRepository.updateByUserCode(request.userCode, request)
    }*/
    @Transactional
    fun deleteUser(userCode: String) {
        userRepository.deleteByUserCode(userCode)
    }
}