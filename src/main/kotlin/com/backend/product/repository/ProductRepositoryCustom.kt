package com.backend.product.repository

import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.res.ProductPageResDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryCustom {
    fun productPage(pageable: Pageable, categoryReqDto: CategoryReqDto): Page<ProductPageResDto>?

}