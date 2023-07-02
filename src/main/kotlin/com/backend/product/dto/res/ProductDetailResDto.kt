package com.backend.product.dto.res

class ProductDetailResDto(
    val productName: String,
    val retailPrice: Int,
    val productDetail: List<ProductOptionResDto>,
    val thumbsImages: List<ProductImagesResDto>,
    val detailImages: List<ProductImagesResDto>
) {

}
