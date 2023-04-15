package com.backend.util

class ResponseDto<T>(
    val code: Int,
    val msg: String?,
    val data: T?,
) {
}