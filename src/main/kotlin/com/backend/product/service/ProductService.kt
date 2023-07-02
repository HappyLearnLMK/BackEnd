package com.backend.product.service

import com.backend.handler.ex.CategoryNotFoundException
import com.backend.handler.ex.ProductNotFoundException
import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.req.ProductPageReqDto
import com.backend.product.dto.req.ProductSaveReqDto
import com.backend.product.dto.res.ProductDetailResDto
import com.backend.product.dto.res.ProductImagesResDto
import com.backend.product.dto.res.ProductOptionResDto
import com.backend.product.dto.res.ProductPageResDto
import com.backend.product.repository.CategoryRepository
import com.backend.product.repository.ProductImageRepository
import com.backend.product.repository.ProductOptionRepository
import com.backend.product.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productOptionRepository: ProductOptionRepository,
    private val categoryRepository: CategoryRepository,
    private val productImageRepository: ProductImageRepository
) {

    @Transactional
    fun saveProduct(productDto: ProductSaveReqDto): Product {
        val findCategory =
            categoryRepository.findByMainCategoryAndMiddleCategory(productDto.mainCategory, productDto.middleCategory)
                ?: throw CategoryNotFoundException()
        val product = productDto.saveProduct(findCategory)

        if (productDto.productOptionsReqDtoList.isEmpty()) {
            productRepository.save(product)
        } else {
            val productOptions = productDto.productOptionsReqDtoList.map { optionsDto ->
                val productOption = ProductOption(optionsDto)
                productOption.addProduct(product)
                productOption
            }.toMutableList()

            productRepository.save(product)
            productOptionRepository.saveAll(productOptions)
        }
        return product
    }

    @Transactional
    fun productCategoryPage(
        productPageReqDto: ProductPageReqDto,
        categoryReqDto: CategoryReqDto
    ): Page<ProductPageResDto>? {
        val pageRequest = PageRequest.of(productPageReqDto.page, productPageReqDto.size)
        return productRepository.productCategoryPage(pageRequest, categoryReqDto)
    }

    @Transactional
    fun productDetailPage(productCode: String): ProductDetailResDto? {
        val product = productRepository.findByIdOrNull(productCode) ?: throw ProductNotFoundException()

        val productOptionList =
            productOptionRepository.findAllByProduct(product)
                .map { ProductOptionResDto(it.optionName, it.optionValue, it.currentQuantity) }

        val findAllByProduct = productImageRepository.findAllByProduct(product)

        val thumbsImages = findAllByProduct
            .filter { it.type == "THUMBS" }
            .map { ProductImagesResDto(it.originalFileName, it.saveFilename) }

        val detailImages = findAllByProduct
            .filter { it.type == "DETAIL" }
            .map { ProductImagesResDto(it.originalFileName, it.saveFilename) }

        return ProductDetailResDto(
            product.productName,
            product.retailPrice,
            productOptionList,
            thumbsImages,
            detailImages
        )
    }
}