package com.backend.productReview.dto.request

import jakarta.validation.constraints.Email

class ProductReviewSaveRequestDto(
    val productSeq : String,
    @field:Email
    val memberId: String,
    val contents: String,
    val score: Int,
    val like: Int,
) {
}