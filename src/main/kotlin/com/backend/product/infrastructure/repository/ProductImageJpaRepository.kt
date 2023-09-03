package com.backend.product.infrastructure.repository

import com.backend.product.infrastructure.entity.ProductEntity
import com.backend.product.infrastructure.entity.ProductImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageJpaRepository : JpaRepository<ProductImageEntity, Long?> {

    fun findAllByProduct(product: ProductEntity) : List<ProductImageEntity>
}