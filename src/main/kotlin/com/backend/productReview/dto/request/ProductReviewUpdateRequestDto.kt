package com.backend.productReview.dto.request

class ProductReviewUpdateRequestDto(
    val reviewSeq:String,
    val contents: String,
    val score: Int,
    var like: Int,
    var answer: String,
) {

}