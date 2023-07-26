package com.backend.config.security.auth

import com.backend.user.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class LoginUser(
    val user: User
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        authorities.add { "ROLE_" + user.type }
        return authorities
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}