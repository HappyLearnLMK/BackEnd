package com.backend.productqna.dto.req

import jakarta.validation.constraints.Email

class SaveAnswerReqDto(
    val qnaSeq: Long,
    @field:Email
    val memberId: String,
    val content: String
) {

}
