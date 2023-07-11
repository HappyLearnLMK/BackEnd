package com.backend.productReview.repository

import com.backend.productReview.domain.ProductReview
import org.springframework.data.jpa.repository.JpaRepository

interface ProductReviewRepository: JpaRepository<ProductReview, Long> {
    fun deleteByReviewSeq(reviewSeq:String):Int
    fun findFirstByOrderByProductSeqDesc(): ProductReview
    fun findAllByMemberId(memberId: String): MutableList<ProductReview>
    fun findAllByProductSeq(productSeq:String): MutableList<ProductReview>
    fun findByReviewSeq(reviewSeq: String): ProductReview
}