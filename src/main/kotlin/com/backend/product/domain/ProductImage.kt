package com.backend.product.domain

class ProductImage(
    val saveFilename: String,
    val originalFileName: String,
    val type: String,
    val product: Product,
    var id: Long? = null
) {
}