package com.backend.product.dto.req

import com.backend.product.domain.Category
import com.backend.product.domain.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

class ProductSaveReqDto (
     var mainCategory: String,
     var middleCategory: String,
     @field:NotEmpty
     val productName: String,
     val wholePrice: Int,
     val retailPrice: Int,
     @field:NotNull
     val saleYn: Boolean,
     val productOptionsReqDtoList: MutableList<ProductOptionReqDto>
){

     fun saveProduct(findCategory: Category?): Product{
          val saveProductUUid = UUID.randomUUID().toString()
          return Product(this.productName, this.wholePrice, this.retailPrice,this.saleYn,
               category = findCategory, productCode = saveProductUUid)
     }
}