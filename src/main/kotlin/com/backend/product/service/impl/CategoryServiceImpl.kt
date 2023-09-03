package com.backend.product.service.impl

import com.backend.handler.ex.CategoryNotFoundException
import com.backend.product.domain.Category
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.service.CategoryService
import com.backend.product.service.port.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
) : CategoryService {

    @Transactional
    override fun create(categoryReqDto: CategoryReqDto): Category {
        val category = Category.from(categoryReqDto)
        return categoryRepository.save(category)
    }

    @Transactional
    override fun getByMainCategoryAndMiddleCategory(mainCategory: String, middleCategory: String): Category {
       return categoryRepository.findByMainCategoryAndMiddleCategory(
            mainCategory,
            middleCategory
        ) ?: throw CategoryNotFoundException()
    }
}