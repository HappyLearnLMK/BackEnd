package com.backend.product.domain

import jakarta.persistence.*

@Entity
class Product(

    @Column(name = "PRODUCT_NAME", nullable = false)
    val productName: String,
    @Column(name = "WHOLE_PRICE")
    val wholePrice: Int,
    @Column(name = "RETAIL_PRICE")
    val retailPrice: Int,
    @Column(name = "SALES_YN", nullable = false)
    val salesYn: Boolean,

    @OneToMany(mappedBy = "productOptionSeq", fetch = FetchType.LAZY)
    val productOptions: MutableList<ProductOption> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_CODE")
    val category: Category?,

    @Id
    @Column(name = "PRODUCT_CODE")
    var productCode: String,
) {
}