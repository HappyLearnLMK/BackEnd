package com.backend.product.repository

import com.backend.product.domain.QCategory.Companion.category
import com.backend.product.domain.QProduct.Companion.product
import com.backend.product.domain.QProductImage.Companion.productImage
import com.backend.product.domain.QProductOption.Companion.productOption
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.res.ProductDetailResDto
import com.backend.product.dto.res.ProductImagesResDto
import com.backend.product.dto.res.ProductOptionResDto
import com.backend.product.dto.res.ProductPageResDto
import com.querydsl.core.group.GroupBy.*
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class ProductRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ProductRepositoryCustom {

    override fun productCategoryPage(pageable: Pageable, categoryReqDto: CategoryReqDto): Page<ProductPageResDto>? {
        val content = jpaQueryFactory.select(
            Projections.constructor(
                ProductPageResDto::class.java,
                product.productCode,
                product.productName,
                productImage.originalFileName,
                productImage.saveFilename,
            ),
        )
            .from(product)
            .leftJoin(productImage)
            .on(productImage.product.productCode.eq(product.productCode))
            .leftJoin(category)
            .on(category.categoryCode.eq(product.category.categoryCode))
            .where(
                category.mainCategory.eq(categoryReqDto.mainCategory),
                category.middleCategory.eq(categoryReqDto.middleCategory),
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val totalCount = jpaQueryFactory.select(
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

    override fun productDetailPage(productCode: String): ProductDetailResDto? {
        return  jpaQueryFactory
            .from(product)
            .leftJoin(productOption)
            .on(productOption.product.productCode.eq(product.productCode))
            .leftJoin(productImage)
            .on(productImage.product.productCode.eq(product.productCode))
            .where(product.productCode.eq(productCode))
            .transform(
                groupBy(product.productName).list(
                    Projections.constructor(
                        ProductDetailResDto::class.java,
                        product.productName,
                        product.retailPrice,
                        list(
                            Projections.constructor(
                                ProductOptionResDto::class.java,
                                productOption.optionName,
                                productOption.optionValue,
                                productOption.currentQuantity,
                            )
                        ),
                        set(
                            Projections.constructor(
                                ProductImagesResDto::class.java,
                                productImage.originalFileName,
                                productImage.saveFilename
                            )
                        )
                    )
                )
            )[0]
    }
}