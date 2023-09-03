package com.backend.product.service

import com.backend.product.domain.Category
import com.backend.product.dto.req.CategoryReqDto

interface CategoryService {
    fun create(categoryReqDto: CategoryReqDto): Category

    fun getByMainCategoryAndMiddleCategory(mainCategory: String, middleCategory: String): Category
}