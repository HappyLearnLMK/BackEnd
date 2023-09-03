package com.backend.product.domain

import com.backend.product.dto.req.ProductOptionReqDto
import com.backend.product.dto.res.ProductOptionResDto

class ProductOption(
    val optionName: String,
    val optionValue: String,
    val currentQuantity: Int,
    val product: Product,
    val productOptionSeq: Long? = null,
) {
    companion object {
        fun from(productOptionReq: ProductOptionReqDto, product: Product): ProductOption {
            return ProductOption(
                optionName = productOptionReq.optionName,
                optionValue = productOptionReq.optionVale,
                product = product,
                currentQuantity = productOptionReq.currentQuantity
            )
        }
    }
}
