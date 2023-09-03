package com.backend.product.service.port

import com.backend.product.domain.Product
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.res.ProductPageResDto
import com.backend.product.infrastructure.entity.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ProductRepository {
    fun save(product: Product): Product
    fun productCategoryPage(pageRequest: PageRequest, categoryReqDto: CategoryReqDto): Page<ProductPageResDto>?
    fun findByCode(productCode: String): Product
}