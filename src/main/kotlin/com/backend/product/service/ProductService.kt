package com.backend.product.service

import com.backend.product.domain.Product
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.req.ProductPageReqDto
import com.backend.product.dto.req.ProductSaveReqDto
import com.backend.product.dto.res.ProductDetailResDto
import com.backend.product.dto.res.ProductPageResDto
import org.springframework.data.domain.Page

interface ProductService {

    fun saveProduct(productSaveReq: ProductSaveReqDto): Product

    fun productCategoryPage(
        productPageReqDto: ProductPageReqDto,
        categoryReqDto: CategoryReqDto
    ): Page<ProductPageResDto>?

    fun productDetailPage(productCode: String): ProductDetailResDto?
}