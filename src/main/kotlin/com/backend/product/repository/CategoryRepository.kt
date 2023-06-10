package com.backend.product.repository

import com.backend.product.domain.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByMainCategoryAndMiddleCategory(mainCategory: String, middleCategory: String): Category?
}