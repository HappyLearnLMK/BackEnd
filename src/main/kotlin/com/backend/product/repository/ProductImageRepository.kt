package com.backend.product.repository

import com.backend.product.domain.ProductImage
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository : JpaRepository<ProductImage, Long?> {
}