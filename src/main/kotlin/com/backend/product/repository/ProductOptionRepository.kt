package com.backend.product.repository

import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductOptionRepository : JpaRepository<ProductOption, Long> {

    fun findAllByProduct(product: Product) : List<ProductOption>
}