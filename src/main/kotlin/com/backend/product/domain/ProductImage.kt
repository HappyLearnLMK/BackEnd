package com.backend.product.domain

import jakarta.persistence.*

@Entity
class ProductImage(

    @Column
    val saveFilename: String,

    @Column
    val originalFileName: String,

    @Column
    val type: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_CODE")
    val product: Product,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {


}