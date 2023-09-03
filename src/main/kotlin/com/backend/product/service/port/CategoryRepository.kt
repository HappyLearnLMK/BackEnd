package com.backend.product.service.port

import com.backend.product.domain.Category
import com.backend.product.infrastructure.entity.CategoryEntity

interface CategoryRepository {
    fun findByMainCategoryAndMiddleCategory(mainCategory: String, middleCategory: String): Category?

    fun save(category: Category): Category

}