package com.backend.product.service

import com.backend.product.domain.Category
import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.fake.FakeCategoryRepository
import com.backend.product.fake.FakeProductOptionRepository
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class ProductOptionServiceTest : AnnotationSpec() {
    private val productOptionService: ProductOptionService

    private val category: Category?
    private val product: Product

    init {
        val fakeCategoryRepository = FakeCategoryRepository()
        fakeCategoryRepository.save(Category("outer", "cloths"))

        this.category =
            fakeCategoryRepository.findByMainCategoryAndMiddleCategory("cloths", "outer")

        this.product =
            Product(
                "test", 100, 10, true,
                this.category!!,
                UUID.randomUUID().toString()
            )

        val fakeProductOptionRepository = FakeProductOptionRepository()

        this.productOptionService = ProductOptionService(fakeProductOptionRepository)
    }

    @Test
    fun saveAll() {
        val productOptionList = mutableListOf<ProductOption>()
        for (i in 1..10) {
            productOptionList.add(ProductOption("test${i}", "$i", i, this.product))
        }

        // when
        val saveAll = productOptionService.saveAll(productOptionList)

        // then
        assertThat(saveAll.size).isEqualTo(10)
    }

    @Test
    fun getAllOption(){
        // given
        val productOptionList = mutableListOf<ProductOption>()
        for (i in 1..10) {
            productOptionList.add(ProductOption("test${i}", "$i", i, this.product))
        }

        productOptionService.saveAll(productOptionList)

        // when
        val allOption = productOptionService.getAllOption(product)

        // then
        assertThat(allOption.size).isEqualTo(10)
    }
}