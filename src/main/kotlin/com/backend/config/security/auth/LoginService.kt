package com.backend.config.security.auth

import com.backend.user.repository.UserRepository
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}
@Service
class LoginService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        log.info { "username = {$username}"  }
        val user = userRepository.findByUserName(username)
        return LoginUser(user)
    }
}