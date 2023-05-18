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
                            createNewUserCode())
        )
    }

    private fun createNewUserCode(): String {
        val lastUser = userRepository.findFirstByOrderByUserCodeDesc()
        val lastUserCode = lastUser.userCode
        val firstLetter = lastUserCode!!.substring(0, 1)
        val fromSecondLetter = lastUserCode!!.substring(1, lastUserCode.length).toInt()+1
        return  firstLetter+fromSecondLetter
    }

    @Transactional
    fun getUsers(): MutableList<User> {
        return userRepository.findAll()
    }
    @Transactional
    fun updateUser(request: UserUpdateRequest) {
        val user = userRepository.findByUserCode(request.userCode)
        user.userName = request.userName
        user.mobile = request.mobile
        user.gender = request.gender
        user.type = request.type
        userRepository.save(user)
    }
    @Transactional
    fun deleteUser(userCode: String) {
        userRepository.deleteByUserCode(userCode)
    }
}