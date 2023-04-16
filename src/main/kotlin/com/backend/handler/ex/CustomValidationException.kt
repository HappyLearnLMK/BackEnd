package com.backend.handler.ex

class CustomValidationException(
    private val msg: String,
    val errorMap: MutableMap<String, String?>
) : RuntimeException(msg) {

}