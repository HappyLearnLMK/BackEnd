package com.backend.product.service

import com.backend.product.domain.Category
import com.backend.product.domain.Product
import com.backend.product.fake.FakeCategoryRepository
import com.backend.product.fake.FakeProductImageRepository
import com.backend.util.file.UploadFile
import io.kotest.core.spec.style.AnnotationSpec
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class ProductImageServiceTest : AnnotationSpec() {
    private val productImageService: ProductImageService

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

        val fakeProductImageRepository = FakeProductImageRepository()
        this.productImageService = ProductImageService(fakeProductImageRepository)
    }

    @Test
    fun saveAll_And_GetAllImages() {
        // given
        val uploadFiles = mutableListOf<UploadFile>()
        for (i in 1..10) {
            uploadFiles.add(
                UploadFile(
                    "test-dbFileName${i}",
                    "test-originFilename${i}"
                )
            )
        }

        // when
        productImageService.saveAll(uploadFiles, "THUMBS", this.product)

        // then
        assertThat(productImageService.getAllImage(this.product).size).isEqualTo(10)
    }
}