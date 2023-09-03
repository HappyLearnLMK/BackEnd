package com.backend.product.dto.req

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
class ProductSaveReqDto (
    val mainCategory: String,
    val middleCategory: String,
    @field:NotEmpty
    val productName: String,
    val wholePrice: Int,
    val retailPrice: Int,
    @field:NotNull
    val saleYn: Boolean,
    val productOptionsReqDtoList: MutableList<ProductOptionReqDto>
){

}
class ProductPageReqDto(
    var mainCategory: String,
    var middleCategory: String,
    var page: Int = 0,
    var size: Int = 8,
) {

}
