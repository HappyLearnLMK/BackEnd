package com.backend.productqna.dto.req

import com.backend.productqna.domain.ProductQna
import com.backend.productqna.domain.QnaType
import jakarta.validation.constraints.Email

class SaveQuestionReqDto(
    val productCode: String,
    @field:Email
    val userId: String,
    val qnaType: QnaType,
    val content: String,
    val secretYN: String
) {

    fun toEntity(): ProductQna {
        return ProductQna(
            productCode = productCode,
            customerId = userId,
            qnaType = qnaType,
            question = content,
            secretYN = secretYN
        )
    }
}
