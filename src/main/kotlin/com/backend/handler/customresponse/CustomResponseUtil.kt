package com.backend.handler.customresponse

import com.backend.util.ResponseDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus

object SecurityUtil{

    fun fail(response: HttpServletResponse?, msg: String, httpStatus: HttpStatus) {
        val responseDto = ResponseDto(-1, msg, null)
        val responseBody = jacksonObjectMapper().writeValueAsString(responseDto)
        response?.contentType = "application/json; charset=utf-8"
        response?.status = httpStatus.value()
        response?.writer?.println(responseBody)
    }
    fun success(response: HttpServletResponse?, dto: Any) {
        val responseDto = ResponseDto(1, "로그인 성공", dto)
        val responseBody = jacksonObjectMapper().writeValueAsString(responseDto)
        response?.contentType = "application/json; charset=utf-8"
        response?.status = 200
        response?.writer?.println(responseBody)
    }
}