package com.backend.handler.ex

import org.springframework.http.HttpStatus

class CustomValidationException(
    override val message: String = "유효성 검사 실패",
    val errorMap: MutableMap<String, String?>
) : RuntimeException(message)
sealed class CustomApiException(
    val code: Int,
    open val status: HttpStatus,
    override val message: String
) : RuntimeException(message)

class CategoryNotFoundException(
    override val status: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message: String = "Category 가 존재하지 않습니다."
) : CustomApiException(400, status, message)

class ProductNotFoundException(
    override val status: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message: String = "상품이 존재하지 않습니다."
) : CustomApiException(400, status, message)
class UserNotFoundException(
    override val status: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message: String = "유저가 존재하지 않습니다."
) : CustomApiException(400, status, message)

class QnaNotFoundException(
    override val status: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message: String = "해당 QnA가 삭제되었거나 없습니다."
) : CustomApiException(400, status, message)