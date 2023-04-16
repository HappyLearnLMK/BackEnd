package com.backend.product.dto.req

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class ProductReqDto (
     @field:NotEmpty
     val productName: String,
     val wholePrice: Int,
     val retailPrice: Int,
     @field:NotNull
     val saleYn: Boolean,
     val productOptionsReqDtos: MutableList<ProductOptionReqDto>
){

}
