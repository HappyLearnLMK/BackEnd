package com.backend.product.service

import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.dto.req.ProductReqDto
import com.backend.product.repository.ProductOptionRepository
import com.backend.product.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productOptionRepository: ProductOptionRepository,
) {

    @Transactional
    fun saveProduct(productDto: ProductReqDto, file: MultipartFile?): Product {
        val product = Product(UUID.randomUUID().toString(), productDto)

        if (productDto.productOptionsReqDtos.isEmpty()) {
            productRepository.save(product)
        } else {
            val productOptions = productDto.productOptionsReqDtos.map { optionsDto ->
                val productOption = ProductOption(optionsDto)
                productOption.addProduct(product)
                productOption
            }.toMutableList()

            productRepository.save(product)
            productOptionRepository.saveAll(productOptions)
        }
        return product
    }
}