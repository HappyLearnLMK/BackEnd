package com.backend.product.service.port

import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption

interface ProductOptionRepository {
    fun save(productOption: ProductOption): ProductOption

    fun saveAll(productOptions: List<ProductOption>): List<ProductOption>

    fun findAllByProduct(product: Product) : List<ProductOption>
    fun removeAll()
}