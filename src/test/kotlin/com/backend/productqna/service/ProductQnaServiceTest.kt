package com.backend.productqna.service

import com.backend.productqna.domain.ProductQna
import com.backend.productqna.domain.QnaType
import com.backend.productqna.repository.ProductQnaRepository
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductQnaServiceTest @Autowired constructor(
    val productQnaService: ProductQnaService,
    val productQnaRepository: ProductQnaRepository,
    val entityManager: EntityManager
){
    @BeforeAll
    fun before() {
        val productQna1 = ProductQna(
            "product_test1",
            "custmer_test1@naver.com",
            "",
            QnaType.STOCK,
            "N",
            "TestQuestion",
            "",
            1L
        )
        val productQna2 = ProductQna(
            "product_test2",
            "custmer_test2@naver.com",
            "Seller_test2@naver.com",
            QnaType.STOCK,
            "N",
            "TestQuestion2",
            "TestAnswer3",
            2L
        )
        val productQna3 = ProductQna(
            "product_test2",
            "custmer_test3@naver.com",
            "Seller_test3@naver.com",
            QnaType.DELIVERY,
            "N",
            "",
            "",
            3L
        )
        productQnaRepository.saveAll(listOf(productQna1, productQna2, productQna3))
    }

    @AfterAll
    fun after(){
        productQnaRepository.deleteAll()
    }
    @Test
    fun saveAnswer_test(){
        // given
        val productQnaSeq = 1L
        val productQna = productQnaRepository.findByIdOrNull(productQnaSeq)

        // when
        productQna?.answer  = "TestAnswer"
        productQna?.sellerId = "Seller_test@naver.com"
        entityManager.flush()
        entityManager.clear()

        val findAnswer = productQnaRepository.findByIdOrNull(productQnaSeq)
        // then
        assertThat(findAnswer?.sellerId).isEqualTo("Seller_test@naver.com")
        assertThat(findAnswer?.answer).isEqualTo("TestAnswer")

    }

    @Test
    fun qnaList_test(){
        // given
        val testProductCode = "product_test2"

        // when
        val qnaList = productQnaService.qnaList(testProductCode)

        // then
        assertThat(qnaList.size).isEqualTo(2)
    }
}