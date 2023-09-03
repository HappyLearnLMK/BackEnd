package com.backend.product.infrastructure.repository.impl

import com.backend.product.domain.Product
import com.backend.product.domain.ProductImage
import com.backend.product.infrastructure.entity.ProductEntity
import com.backend.product.infrastructure.entity.ProductImageEntity
import com.backend.product.infrastructure.repository.ProductImageJpaRepository
import com.backend.product.service.port.ProductImageRepository
import org.springframework.stereotype.Repository

@Repository
class ProductImageRepositoryImpl(
    private val productImageJpaRepository: ProductImageJpaRepository
) : ProductImageRepository{
    override fun findById(productImageId: Long): ProductImage {
        return productImageJpaRepository.findById(productImageId).get().to()
    }

    override fun save(productImage: ProductImage): ProductImage {
       return productImageJpaRepository.save(ProductImageEntity.from(productImage)).to()
    }

    override fun saveAll(productImages: List<ProductImage>): List<ProductImage> {
        return productImageJpaRepository.saveAll(productImages.map { ProductImageEntity.from(it) }).map { it.to() }
    }

    override fun findAllByProduct(product: Product): List<ProductImage> {
        return productImageJpaRepository.findAllByProduct(ProductEntity.from(product)).map { it.to() }
    }


}