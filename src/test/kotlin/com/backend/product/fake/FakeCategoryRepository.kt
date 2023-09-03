package com.backend.product.fake

import com.backend.product.domain.Category
import com.backend.product.service.port.CategoryRepository
import java.util.concurrent.atomic.AtomicLong

class FakeCategoryRepository(
    private val generatedId: AtomicLong = AtomicLong(0L),
    private val data: MutableList<Category> = mutableListOf()
) : CategoryRepository {
    override fun findByMainCategoryAndMiddleCategory(mainCategory: String, middleCategory: String): Category? {
        val categoryList = data.filter { it.mainCategory == mainCategory && it.middleCategory == middleCategory }
        if (categoryList.isEmpty()) {
            return null
        }
        return categoryList.last()
    }

    override fun save(category: Category): Category {
        return if (category.categoryCode == null || category.categoryCode == 0L) {
            val newCategory = Category(category.middleCategory, category.mainCategory, generatedId.incrementAndGet())
            data.add(newCategory)
            newCategory
        }else{
            data.removeIf{ it.categoryCode == category.categoryCode}
            data.add(category)
            category
        }
    }
}
