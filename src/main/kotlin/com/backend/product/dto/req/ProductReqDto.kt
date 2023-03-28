package com.backend.product.dto.req

class ProductReqDto (
     val productName: String,
     val wholePrice: Int,
     val retailPrice: Int,
     val saleYn: Boolean,
     val productImg: String,
     val productOptionsReqDtos: MutableList<ProductOptionReqDto>
){

}
