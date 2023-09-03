package com.backend.product.dto.res
class ProductDetailResDto(
    val productName: String,
    val retailPrice: Int,
    val productDetail: List<ProductOptionResDto>,
    val thumbsImages: List<ProductImagesResDto>,
    val detailImages: List<ProductImagesResDto>
) {

}

class ProductPageResDto(
    val productCode: String,
    val productName: String,
    val originalFileName: String,
    val saveFilename: String
) {
}
