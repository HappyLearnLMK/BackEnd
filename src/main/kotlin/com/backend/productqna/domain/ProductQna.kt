package com.backend.productqna.domain

import jakarta.persistence.*

@Entity
class ProductQna(

    var productCode: String = "",

    var customerId: String = "",

    var sellerId: String = "",

    @Enumerated(EnumType.STRING)
    var qnaType: QnaType,

    var secretYN: String = "N",

    var question: String = "",

    var answer: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val qnaSeq: Long? = null
) {
}