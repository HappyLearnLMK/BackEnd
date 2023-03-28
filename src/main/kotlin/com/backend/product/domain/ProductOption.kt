package com.backend.product.domain

import com.backend.product.dto.req.ProductOptionReqDto
import jakarta.persistence.*

@Entity
@Table(name = "PRODUCT_OPTION")
class ProductOption(

    @Column
    val optionName: String,
    @Column
    val optionValue: String,
    @Column
    val currentQuantity: String,

    @ManyToOne
    @JoinColumn(name = "PRODUCT_CODE")
    var product: Product? = null,

    @Id
    @Column(name = "PRODUCT_OPTION_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productOptionSeq: Long? = null,
) {
    constructor(optionReqDto: ProductOptionReqDto) : this(optionReqDto.optionName, optionReqDto.optionVale, optionReqDto.currentQuantity)

    fun addProduct(product: Product) {
        this.product = product
        product.productOptions.add(this)
    }
}