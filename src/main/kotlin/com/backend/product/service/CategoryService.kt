package com.backend.product.service

import com.backend.product.domain.Category
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun saveCategory(categoryReqDto: CategoryReqDto): Category {
        return categoryRepository.save(Category(categoryReqDto.middleCategory, categoryReqDto.mainCategory))
    }
}