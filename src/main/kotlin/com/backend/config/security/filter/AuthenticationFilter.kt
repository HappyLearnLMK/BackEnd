package com.backend.config.security.filter

import com.backend.config.security.auth.LoginReqDto
import com.backend.config.security.auth.LoginResDto
import com.backend.config.security.auth.LoginUser
import com.backend.config.security.jwt.JwtProcess
import com.backend.config.security.jwt.JwtVO
import com.backend.handler.customresponse.SecurityUtil
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

private val log = KotlinLogging.logger {}
class AuthenticationFilter(
    private val authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    init {
        setFilterProcessesUrl("/api/login")
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        log.info { "attemptAuthentication Call" }
        return try {
            val loginReqDto = jacksonObjectMapper()
                .readValue(request.inputStream, LoginReqDto::class.java)

            val authenticationToken = UsernamePasswordAuthenticationToken(
                loginReqDto.username,
                loginReqDto.password
            )

            authenticationManager.authenticate(authenticationToken)
        } catch (e: Exception) {
            throw InternalAuthenticationServiceException(e.message)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val loginUser = authResult?.principal as LoginUser
        log.info { "success Login = {$loginUser}" }
        val jwtToken = JwtProcess.generateToken(loginUser)
        response?.addHeader(JwtVO.HEADER, jwtToken)

        val loginResDto = LoginResDto(loginUser.username, loginUser.user.userId)
        SecurityUtil.success(response, loginResDto)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        log.info { "fail Login" }
    }
}