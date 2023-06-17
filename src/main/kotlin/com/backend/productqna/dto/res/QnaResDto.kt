package com.backend.productqna.dto.res

import com.backend.productqna.domain.ProductQna
import com.backend.productqna.domain.QnaType

class QnaResDto(
    val qnaSeq: Long,
    val customerId: String,
    val sellerId: String,
    val qnaType: QnaType,
    val secretYN: String,
    val question: String,
    val answer: String
) {

    constructor(productQna: ProductQna) : this(
        qnaSeq = productQna.qnaSeq!!,
        customerId = productQna.customerId,
        sellerId = productQna.sellerId,
        qnaType = productQna.qnaType,
        secretYN = productQna.secretYN,
        question = productQna.question,
        answer = productQna.answer
    )
}
