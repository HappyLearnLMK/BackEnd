package com.backend.config.security

import com.backend.config.security.auth.LoginService
import com.backend.config.security.filter.AuthenticationFilter
import com.backend.config.security.filter.AuthorizationFilter
import com.backend.handler.customresponse.SecurityUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val loginService: LoginService
) {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.headers().frameOptions().disable()
        http.csrf().disable()

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.formLogin().disable()
        http.httpBasic().disable()

        http.apply(CustomSecurityFilterManager())

        http.exceptionHandling().accessDeniedHandler { _, response, _ ->
            run {
                SecurityUtil.fail(response, "접근 제한", HttpStatus.FORBIDDEN)
            }
        }

        http.authorizeHttpRequests()
            .requestMatchers("/test/**").authenticated()
            .anyRequest().permitAll()

        return http.build()
    }
}

class CustomSecurityFilterManager : AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        val authenticationManager = builder.getSharedObject(AuthenticationManager::class.java)
        builder.addFilter(AuthenticationFilter(authenticationManager))
        builder.addFilter(AuthorizationFilter(authenticationManager))
        super.configure(builder)
    }
}