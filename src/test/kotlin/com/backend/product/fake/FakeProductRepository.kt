package com.backend.product.fake

import com.backend.product.domain.Product
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.res.ProductPageResDto
import com.backend.product.service.port.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

class FakeProductRepository : ProductRepository {
    override fun save(product: Product): Product {
        TODO("Not yet implemented")
    }

    override fun productCategoryPage(
        pageRequest: PageRequest,
        categoryReqDto: CategoryReqDto
    ): Page<ProductPageResDto>? {
        TODO("Not yet implemented")
    }

    override fun findByCode(productCode: String): Product {
        TODO("Not yet implemented")
    }


}
