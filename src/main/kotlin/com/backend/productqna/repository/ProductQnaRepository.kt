package com.backend.productqna.repository

import com.backend.productqna.domain.ProductQna
import org.springframework.data.jpa.repository.JpaRepository

interface ProductQnaRepository : JpaRepository<ProductQna, Long> {
    fun findAllByProductCode(productCode: String): List<ProductQna>
}