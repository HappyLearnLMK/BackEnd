package com.backend.product.infrastructure.repository

import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.res.ProductDetailResDto
import com.backend.product.dto.res.ProductPageResDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryCustom {
    fun productCategoryPage(pageable: Pageable, categoryReqDto: CategoryReqDto): Page<ProductPageResDto>

    fun productDetailPage(productCode: String): ProductDetailResDto?
}