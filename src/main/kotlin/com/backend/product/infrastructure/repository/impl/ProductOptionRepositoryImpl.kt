package com.backend.product.infrastructure.repository.impl

import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.infrastructure.entity.ProductEntity
import com.backend.product.infrastructure.entity.ProductOptionEntity
import com.backend.product.infrastructure.repository.ProductOptionJpaRepository
import com.backend.product.service.port.ProductOptionRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
class ProductOptionRepositoryImpl(
    private val productOptionJpaRepository: ProductOptionJpaRepository
) : ProductOptionRepository {
    override fun save(productOption: ProductOption): ProductOption {
        return productOptionJpaRepository.save(ProductOptionEntity.from(productOption)).to()
    }

    override fun saveAll(productOptions: List<ProductOption>): List<ProductOption> {
        return productOptionJpaRepository.saveAll(
            productOptions.map { ProductOptionEntity.from(it) })
            .map { it.to() }
    }

    override fun findAllByProduct(product: Product): List<ProductOption> {
        return productOptionJpaRepository.findAllByProduct(ProductEntity.from(product)).map { it.to() }
    }

    override fun removeAll() {
        TODO("Not yet implemented")
    }


}