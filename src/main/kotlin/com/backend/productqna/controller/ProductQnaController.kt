package com.backend.productqna.controller

import com.backend.productqna.dto.req.SaveAnswerReqDto
import com.backend.productqna.dto.req.SaveQuestionReqDto
import com.backend.productqna.service.ProductQnaService
import com.backend.util.ResponseDto
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product/qna")
class ProductQnaController(
    private val productQnaService: ProductQnaService
) {

    @PostMapping("/question")
    fun saveQuestion(saveQuestionReqDto: SaveQuestionReqDto): ResponseEntity<*> {
        productQnaService.saveQuestion(saveQuestionReqDto)
        return ResponseEntity(ResponseDto(1, "Qna 질문 등록 완료", null), HttpStatus.CREATED)
    }

    @PostMapping("/answer")
    fun saveAnswer(saveAnswerReqDto: SaveAnswerReqDto): ResponseEntity<*> {
        productQnaService.saveAnswer(saveAnswerReqDto)
        return ResponseEntity(ResponseDto(1, "Qna 답면 등록 완료", null), HttpStatus.CREATED)
    }

    @GetMapping("/qna")
    fun productQna(@Param("productCode") productCode: String): ResponseEntity<*> {
        val qnaList = productQnaService.qnaList(productCode)
        return ResponseEntity(ResponseDto(1, "Qna 답면 등록 완료", qnaList), HttpStatus.OK)
    }
}