package com.backend.product.domain

import com.backend.product.dto.req.CategoryReqDto


class Category(
    val middleCategory: String,
    val mainCategory: String,
    val categoryCode: Long? = null
) {
    companion object {
        fun from(categoryReqDto: CategoryReqDto): Category{
            return Category(categoryReqDto.middleCategory, categoryReqDto.mainCategory)
        }
    }
}