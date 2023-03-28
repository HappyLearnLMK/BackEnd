package com.backend.product.domain

import com.backend.product.dto.req.ProductReqDto
import jakarta.persistence.*
import java.util.UUID

@Entity
class Product(
    @Id
    @Column(name = "PRODUCT_CODE")
    val productCode: String,
    @Column(name = "PRODUCT_NAME", nullable = false)
    val productName: String,
    @Column(name = "WHOLE_PRICE")
    val wholePrice: Int,
    @Column(name = "RETAIL_PRICE")
    val retailPrice: Int,
    @Column(name = "SALES_YN", nullable = false)
    val salesYn: Boolean,
    @OneToMany(mappedBy = "productOptionSeq", fetch = FetchType.LAZY)
    val productOptions: MutableList<ProductOption> = mutableListOf()

) {

    constructor(genCode: String, productReqDto: ProductReqDto) : this(
        genCode, productReqDto.productName,
        productReqDto.wholePrice, productReqDto.retailPrice, productReqDto.saleYn
    )
}