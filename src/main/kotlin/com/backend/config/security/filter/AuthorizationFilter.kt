package com.backend.config.security.filter

import com.backend.config.security.jwt.JwtProcess
import com.backend.config.security.jwt.JwtVO
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.util.StringUtils

class AuthorizationFilter(
    authenticationManager: AuthenticationManager?
) :
    BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        if (isHeaderVerify(request, response)) {
            val token = request.getHeader(JwtVO.HEADER).replace(JwtVO.TOKEN_PREFIX, "")
            val verify = JwtProcess.verify(token)

            val authentication: Authentication =
                UsernamePasswordAuthenticationToken(verify, null, verify.authorities)

            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }

    private fun isHeaderVerify(request: HttpServletRequest, response: HttpServletResponse): Boolean {
        val header = request.getHeader(JwtVO.HEADER)
        return StringUtils.hasText(header) && header.startsWith(JwtVO.TOKEN_PREFIX)
    }
}