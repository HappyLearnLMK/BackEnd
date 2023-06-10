package com.backend.product.repository

import com.backend.product.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, String>, ProductRepositoryCustom{
}