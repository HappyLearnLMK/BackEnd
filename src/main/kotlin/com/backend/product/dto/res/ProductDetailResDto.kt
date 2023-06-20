package com.backend.product.dto.res

class ProductDetailResDto(
    val productName: String,
    val retailPrice: Int,
    val productDetail: MutableList<ProductOptionResDto>,
    val productImages: MutableSet<ProductImagesResDto>
) {

}
