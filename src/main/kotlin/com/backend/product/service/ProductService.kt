package com.backend.product.service

import com.backend.handler.ex.CustomApiException
import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.req.ProductPageReqDto
import com.backend.product.dto.req.ProductSaveReqDto
import com.backend.product.dto.res.ProductPageResDto
import com.backend.product.repository.CategoryRepository
import com.backend.product.repository.ProductOptionRepository
import com.backend.product.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productOptionRepository: ProductOptionRepository,
    private val categoryRepository: CategoryRepository,
) {

    @Transactional
    fun saveProduct(productDto: ProductSaveReqDto, file: MultipartFile?): Product {
        val findCategory =
            categoryRepository.findByMainCategoryAndMiddleCategory(productDto.mainCategory, productDto.middleCategory)
                ?: throw CustomApiException("Category 가 존재하지 않습니다.")
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
    fun productPage(productPageReqDto: ProductPageReqDto, categoryReqDto: CategoryReqDto): Page<ProductPageResDto>? {
        val pageRequest = PageRequest.of(productPageReqDto.page, productPageReqDto.size)
        return productRepository.productPage(pageRequest, categoryReqDto)
    }
}