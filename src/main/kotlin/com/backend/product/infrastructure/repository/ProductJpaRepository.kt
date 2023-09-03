package com.backend.product.infrastructure.repository

import com.backend.product.domain.Product
import com.backend.product.infrastructure.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ProductJpaRepository : JpaRepository<ProductEntity, Long>, ProductRepositoryCustom{
    fun findByProductCode(productCode: String): Product
}