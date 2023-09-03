package com.backend.product.infrastructure.repository.impl

import com.backend.product.domain.Category
import com.backend.product.infrastructure.entity.CategoryEntity
import com.backend.product.infrastructure.repository.CategoryJpaRepository
import com.backend.product.service.port.CategoryRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Repository
class CategoryRepositoryImpl(
    private val categoryJpaRepository: CategoryJpaRepository
) : CategoryRepository {

    override fun findByMainCategoryAndMiddleCategory(mainCategory: String, middleCategory: String): Category? {
        return categoryJpaRepository.findByMainCategoryAndMiddleCategory(mainCategory, middleCategory)?.to()
    }

    override fun save(category: Category): Category {
        return categoryJpaRepository.save(CategoryEntity.from(category)).to()
    }
}