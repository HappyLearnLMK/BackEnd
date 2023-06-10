package com.backend.product.repository

import com.backend.product.domain.QCategory.Companion.category
import com.backend.product.domain.QProduct.Companion.product
import com.backend.product.domain.QProductImage.Companion.productImage
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.res.ProductPageResDto
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class ProductRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ProductRepositoryCustom {

    override fun productPage(pageable: Pageable, categoryReqDto: CategoryReqDto): Page<ProductPageResDto>? {
        val content = jpaQueryFactory.select(
            Projections.constructor(ProductPageResDto::class.java,
                product.productCode.`as`("productCode"),
                product.productName.`as`("productName"),
                productImage.originalFileName.`as`("originalFileName"),
                productImage.saveFilename.`as`("saveFilename")),
        )
            .from(product)
            .leftJoin(productImage)
            .on(productImage.product.productCode.eq(product.productCode))
            .leftJoin(category)
            .on(category.categoryCode.eq(product.category.categoryCode))
            .where(category.mainCategory.eq(categoryReqDto.mainCategory),
                category.middleCategory.eq(categoryReqDto.middleCategory),)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val totalCount = jpaQueryFactory.select (
            product.productCode.count()
        )
            .from(product)
            .leftJoin(category)
            .on(category.categoryCode.eq(product.category.categoryCode))
            .where(
                category.mainCategory.eq(categoryReqDto.mainCategory),
                category.middleCategory.eq(categoryReqDto.middleCategory)
            )
            .fetchOne()

        return PageImpl(content, pageable, totalCount!!)
    }

}