package com.backend.product.dto.req

class ProductPageReqDto(
    var mainCategory: String,
    var middleCategory: String,
    var page: Int = 0,
    var size: Int = 8,
) {

}
