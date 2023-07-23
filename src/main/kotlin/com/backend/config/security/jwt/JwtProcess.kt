package com.backend.config.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.backend.config.security.auth.LoginUser
import com.backend.config.security.auth.LoginVerifyDto
import com.backend.user.domain.User
import com.backend.user.domain.UserType
import java.time.LocalDate
import java.util.*

object JwtProcess {

    fun generateToken(loginUser: LoginUser): String {
        val jwtToken = JWT.create()
            .withSubject(loginUser.user.userId)
            .withExpiresAt(Date(System.currentTimeMillis() + JwtVO.EXPIRATION_TIME))
            .withClaim("id", loginUser.user.userId)
            .withClaim("name", loginUser.username)
            .withClaim("role", loginUser.user.type.name)
            .sign(Algorithm.HMAC256(JwtVO.SECRET))

        return JwtVO.TOKEN_PREFIX + jwtToken
    }

    fun verify(token: String): LoginUser {
        val decodedJWT = JWT.require(Algorithm.HMAC256(JwtVO.SECRET)).build().verify(token)
        val id = decodedJWT.claims["id"]!!.asString()
        val name = decodedJWT.claims["name"]!!.asString()
        val role = decodedJWT.claims["role"]!!.asString()
        val user = User(name, LocalDate.now(), "", LocalDate.now(), 'F', UserType.valueOf(role), id, "", null)
        return LoginUser(user)
    }

}