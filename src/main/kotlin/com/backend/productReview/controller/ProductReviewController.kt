package com.backend.productReview.controller

import com.backend.productReview.dto.request.ProductReviewSaveRequestDto
import com.backend.productReview.dto.request.ProductReviewUpdateRequestDto
import com.backend.productReview.service.ProductReviewService
import com.backend.productqna.dto.req.SaveAnswerReqDto
import com.backend.productqna.dto.req.SaveQuestionReqDto
import com.backend.util.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductReviewController(
    private val productReviewService : ProductReviewService
) {

    @PostMapping("/review")
    fun saveReview(productReviewSaveRequestDto: ProductReviewSaveRequestDto): ResponseEntity<*> {
        productReviewService.saveReview(productReviewSaveRequestDto)
        return ResponseEntity(ResponseDto(1, "리뷰 등록 완료", null), HttpStatus.CREATED)
    }

    @PutMapping("/review")
    fun updateReview(productReviewUpdateRequestDto: ProductReviewUpdateRequestDto): ResponseEntity<*> {
        productReviewService.updateReview(productReviewUpdateRequestDto)
        return ResponseEntity(ResponseDto(1, "리뷰 수정 완료", null), HttpStatus.OK)
    }

    @GetMapping("/review")
    fun getReviews(@RequestParam productSeq: String): ResponseEntity<*> {
        val reviewList = productReviewService.getReviews(productSeq)
        return ResponseEntity(ResponseDto(1, "리뷰 리스트 불러오기 완료", reviewList), HttpStatus.OK)
    }

    @GetMapping("/reviewsOfSpecificUser")
    fun getReviewsOfSpecificUser(@RequestParam memberId: String): ResponseEntity<*> {
        val reviewList = productReviewService.getReviewsOfSpecificUser(memberId)
        return ResponseEntity(ResponseDto(1, "유저 리뷰 리스트 불러오기 완료", reviewList), HttpStatus.OK)
    }

    @DeleteMapping("/review")
    fun deleteMember(@RequestParam reviewSeq: String) {
        productReviewService.deleteReview(reviewSeq)
    }
}