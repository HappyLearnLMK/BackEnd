package com.backend.product.infrastructure.repository.impl

import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.res.ProductDetailResDto
import com.backend.product.dto.res.ProductImagesResDto
import com.backend.product.dto.res.ProductOptionResDto
import com.backend.product.dto.res.ProductPageResDto
import com.backend.product.infrastructure.entity.QCategoryEntity.Companion.categoryEntity
import com.backend.product.infrastructure.entity.QProductEntity.Companion.productEntity
import com.backend.product.infrastructure.entity.QProductImageEntity.Companion.productImageEntity
import com.backend.product.infrastructure.entity.QProductOptionEntity.Companion.productOptionEntity
import com.backend.product.infrastructure.repository.ProductRepositoryCustom
import com.querydsl.core.group.GroupBy.*
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class ProductRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ProductRepositoryCustom {
    override fun productCategoryPage(pageable: Pageable, categoryReqDto: CategoryReqDto): Page<ProductPageResDto> {
        val content = jpaQueryFactory.select(
            Projections.constructor(
                ProductPageResDto::class.java,
                productEntity.productCode,
                productEntity.productName,
                productImageEntity.originalFileName,
                productImageEntity.saveFilename,
            ),
        )
            .from(productEntity)
            .leftJoin(productImageEntity)
            .on(productImageEntity.product.productCode.eq(productEntity.productCode))
            .leftJoin(categoryEntity)
            .on(categoryEntity.categoryCode.eq(productEntity.category.categoryCode))
            .where(
                categoryEntity.mainCategory.eq(categoryReqDto.mainCategory),
                categoryEntity.middleCategory.eq(categoryReqDto.middleCategory),
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val totalCount = jpaQueryFactory.select(
            productEntity.productCode.count()
        )
            .from(productEntity)
            .leftJoin(categoryEntity)
            .on(categoryEntity.categoryCode.eq(productEntity.category.categoryCode))
            .where(
                categoryEntity.mainCategory.eq(categoryReqDto.mainCategory),
                categoryEntity.middleCategory.eq(categoryReqDto.middleCategory)
            )
            .fetchOne()

        return PageImpl(content, pageable, totalCount!!)
    }

    override fun productDetailPage(productCode: String): ProductDetailResDto? {
        return jpaQueryFactory
            .from(productEntity)
            .leftJoin(productOptionEntity)
            .on(productOptionEntity.product.productCode.eq(productEntity.productCode))
            .leftJoin(productImageEntity)
            .on(productImageEntity.product.productCode.eq(productEntity.productCode))
            .where(productEntity.productCode.eq(productCode))
            .transform(
                groupBy(productEntity.productName).list(
                    Projections.constructor(
                        ProductDetailResDto::class.java,
                        productEntity.productName,
                        productEntity.retailPrice,
                        list(
                            Projections.constructor(
                                ProductOptionResDto::class.java,
                                productOptionEntity.optionName,
                                productOptionEntity.optionValue,
                                productOptionEntity.currentQuantity,
                            )
                        ),
                        set(
                            Projections.constructor(
                                ProductImagesResDto::class.java,
                                productImageEntity.originalFileName,
                                productImageEntity.saveFilename
                            )
                        )
                    )
                )
            )[0]
    }
}