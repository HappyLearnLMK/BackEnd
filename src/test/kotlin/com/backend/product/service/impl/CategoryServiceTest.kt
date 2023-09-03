package com.backend.product.service.impl

import com.backend.handler.ex.CategoryNotFoundException
import com.backend.product.domain.Category
import com.backend.product.fake.FakeCategoryRepository
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CategoryServiceTest : AnnotationSpec() {

    private val categoryService: CategoryServiceImpl

    init {
        val fakeCategoryRepository = FakeCategoryRepository()
        fakeCategoryRepository.save(Category("outer", "cloths"))
        this.categoryService = CategoryServiceImpl(fakeCategoryRepository)

    }

    @Test
    fun getByMainCategoryAndMiddleCategory() {
        // given
        val middleCategory = "outer"
        val mainCategory = "cloths"

        // when
        val result =
            categoryService.getByMainCategoryAndMiddleCategory(mainCategory, middleCategory)

        // then
        assertThat(result.mainCategory).isEqualTo(mainCategory)
        assertThat(result.middleCategory).isEqualTo(middleCategory)
    }

    @Test
    fun notGetByMainCategoryAndMiddleCategory() {
        // given
        val middleCategory = "outer"
        val mainCategory = "bag"

        // when
        // then
        assertThatThrownBy { categoryService.getByMainCategoryAndMiddleCategory(mainCategory, middleCategory) }
            .isInstanceOf(CategoryNotFoundException::class.java)
    }
}
