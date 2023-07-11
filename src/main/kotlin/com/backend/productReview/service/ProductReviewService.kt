package com.backend.productReview.service

import com.backend.productReview.domain.ProductReview
import com.backend.productReview.dto.request.ProductReviewSaveRequestDto
import com.backend.productReview.dto.request.ProductReviewUpdateRequestDto
import com.backend.productReview.repository.ProductReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductReviewService(
    private val productReviewRepository: ProductReviewRepository,
) {
    @Transactional
    fun saveReview(dto: ProductReviewSaveRequestDto) {
        productReviewRepository.save(ProductReview(dto.productSeq,
            dto.memberId,
            dto.contents,
            dto.score,
            dto.like,
            createNewReviewCode()))
    }

    private fun createNewReviewCode():String{
        val lastReview =  productReviewRepository.findFirstByOrderByProductSeqDesc()
        val lastReviewSeq = lastReview.reviewSeq
        val firstLetter = lastReviewSeq!!.substring(0, 1)
        val fromSecondLetter = lastReviewSeq!!.substring(1, lastReviewSeq.length).toInt()+1
        return firstLetter + fromSecondLetter
    }

    @Transactional
    fun getReviews(productSeq:String): MutableList<ProductReview>{
        return productReviewRepository.findAllByProductSeq(productSeq)
    }

    @Transactional
    fun getReviewsOfSpecificUser(memberId:String): MutableList<ProductReview>{
        return productReviewRepository.findAllByMemberId(memberId)
    }

    @Transactional
    fun updateReview(dto:ProductReviewUpdateRequestDto){
        val review = productReviewRepository.findByReviewSeq(dto.reviewSeq)
        review.contents = dto.contents
        review.answer = dto.answer
        review.like = dto.like
        review.score = dto.score
        productReviewRepository.save(review)
    }

    @Transactional
    fun deleteReview(reviewSeq: String) {
        productReviewRepository.deleteByReviewSeq(reviewSeq)
    }
}