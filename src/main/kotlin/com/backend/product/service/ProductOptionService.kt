package com.backend.product.service

import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.dto.res.ProductOptionResDto
import com.backend.product.service.port.ProductOptionRepository
import org.springframework.stereotype.Service

@Service
class ProductOptionService(
    private val productOptionRepository: ProductOptionRepository
) {
    fun saveAll(productOptions: MutableList<ProductOption>): List<ProductOption> {
        return productOptionRepository.saveAll(productOptions)
    }

    fun getAllOption(product: Product): List<ProductOptionResDto> {
        return productOptionRepository
            .findAllByProduct(product)
            .map { ProductOptionResDto(it.optionName, it.optionValue, it.currentQuantity) }
    }

}
