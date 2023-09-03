package com.backend.product.infrastructure.repository

import com.backend.product.infrastructure.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository : JpaRepository<CategoryEntity, Long> {
    fun findByMainCategoryAndMiddleCategory(mainCategory: String, middleCategory: String): CategoryEntity?
}