package com.backend.product.service

import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.dto.req.ProductReqDto
import com.backend.product.repository.ProductOptionRepository
import com.backend.product.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productOptionRepository: ProductOptionRepository
) {

    @Transactional
    fun saveProduct(productDto: ProductReqDto) {
        val product = Product(UUID.randomUUID().toString(), productDto)

        /*val productOptions = mutableListOf<ProductOption>()
        for (productOptionReqDto in productDto.productOptionsReqDtos) {
            val productOption = ProductOption(productOptionReqDto)
            productOption.addProduct(product)
        }*/

        val productOptions = productDto.productOptionsReqDtos.map { optionsDto ->
            val productOption = ProductOption(optionsDto)
            productOption.addProduct(product)
            productOption
        }.toMutableList()

        productRepository.save(product)
        productOptionRepository.saveAll(productOptions)
    }
}