package com.backend.config.security.jwt

interface JwtVO {

    companion object {
        const val SECRET: String = "SECRET_KEY"
        const val EXPIRATION_TIME: Int = 1000 * 60 * 60 * 24
        const val TOKEN_PREFIX: String = "Bearer "
        const val HEADER: String = "Authorization"

    }
}