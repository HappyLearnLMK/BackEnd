package com.backend.product.infrastructure.repository.impl

import com.backend.product.domain.Product
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.res.ProductPageResDto
import com.backend.product.infrastructure.entity.ProductEntity
import com.backend.product.infrastructure.repository.ProductJpaRepository
import com.backend.product.service.port.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val productJpaRepository: ProductJpaRepository
) : ProductRepository {
    override fun save(product: Product): Product {
        return productJpaRepository.save(ProductEntity.from(product)).to()
    }

    override fun productCategoryPage(
        pageRequest: PageRequest,
        categoryReqDto: CategoryReqDto
    ): Page<ProductPageResDto> {
       return productJpaRepository.productCategoryPage(pageRequest, categoryReqDto)
    }

    override fun findByCode(productCode: String): Product {
        return productJpaRepository.findByProductCode(productCode)
    }


}