package com.backend.product.service.port

import com.backend.product.domain.Product
import com.backend.product.domain.ProductImage

interface ProductImageRepository {

    fun findById(productImageId: Long):ProductImage

    fun save(productImage: ProductImage): ProductImage
    fun saveAll(productImages: List<ProductImage>): List<ProductImage>

    fun findAllByProduct(product: Product) : List<ProductImage>
}