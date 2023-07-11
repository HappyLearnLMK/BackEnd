package com.backend.productReview.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email

@Entity
class ProductReview(
    val productSeq: String,

    @field:Email
    val memberId: String = "",

    var contents: String = "",

    var score: Int = 0,

    var like: Int = 0,

    var answer: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reviewSeq: String? = null
) {
}