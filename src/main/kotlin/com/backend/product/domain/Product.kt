package com.backend.product.domain

import com.backend.product.dto.req.ProductSaveReqDto

class Product(
    val productName: String,
    val wholePrice: Int,
    val retailPrice: Int,
    val salesYn: Boolean,
    val category: Category,
    val productCode: String? = null,
    val id: Long? = null,
) {

    companion object {
        fun from(productSaveReq: ProductSaveReqDto, category: Category): Product {
            return Product(
                productName = productSaveReq.productName,
                wholePrice = productSaveReq.wholePrice,
                retailPrice = productSaveReq.retailPrice,
                salesYn = productSaveReq.saleYn,
                category = category,
            )
        }
    }
}