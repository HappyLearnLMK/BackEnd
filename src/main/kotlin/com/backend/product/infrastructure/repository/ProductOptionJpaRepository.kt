package com.backend.product.infrastructure.repository

import com.backend.product.infrastructure.entity.ProductEntity
import com.backend.product.infrastructure.entity.ProductOptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionJpaRepository : JpaRepository<ProductOptionEntity, Long>{
    fun findAllByProduct(product: ProductEntity) : List<ProductOptionEntity>
}