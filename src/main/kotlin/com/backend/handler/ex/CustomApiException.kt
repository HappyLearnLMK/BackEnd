package com.backend.handler.ex

class CustomApiException(
    private val msg: String,
) : RuntimeException(msg) {

}