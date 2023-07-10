package com.backend.productqna.service

import com.backend.handler.ex.QnaNotFoundException
import com.backend.productqna.dto.req.SaveAnswerReqDto
import com.backend.productqna.dto.req.SaveQuestionReqDto
import com.backend.productqna.dto.res.QnaResDto
import com.backend.productqna.repository.ProductQnaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductQnaService(
    private val productQnaRepository: ProductQnaRepository
) {

    @Transactional
    fun saveQuestion(saveQuestionDto: SaveQuestionReqDto) {
        val toEntity = saveQuestionDto.toEntity()
        productQnaRepository.save(toEntity)
    }

    @Transactional
    fun saveAnswer(saveAnswerReqDto: SaveAnswerReqDto) {
        val productQna = productQnaRepository.findByIdOrNull(saveAnswerReqDto.qnaSeq)
            ?: throw QnaNotFoundException()

        productQna.sellerId = saveAnswerReqDto.userId
        productQna.question = saveAnswerReqDto.content
    }

    fun qnaList(productCode: String): List<QnaResDto> {
        val productQnaList = productQnaRepository.findAllByProductCode(productCode)
        return productQnaList.map { QnaResDto(it) }.toList()
    }
}