package com.backend.handler.ex

import com.backend.handler.ex.CustomApiException
import com.backend.handler.ex.CustomValidationException
import com.backend.util.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(CustomValidationException::class)
    fun validationException(e: CustomValidationException): ResponseEntity<*> {
        return ResponseEntity(ResponseDto(400, e.message, e.errorMap), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CustomApiException::class)
    fun apiException(e: CustomApiException): ResponseEntity<*> {
        return ResponseEntity(ResponseDto(e.code, e.message, null), HttpStatusCode.valueOf(e.code))
    }
}