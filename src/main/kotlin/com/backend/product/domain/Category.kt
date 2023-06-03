package com.backend.product.domain

import jakarta.persistence.*

@Entity
class Category(

    var middleCategory: String,

    var mainCategory: String,

    @Id
    @Column(name = "CATEGORY_CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var categoryCode: Long? = null
) {

}