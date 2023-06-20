package com.backend.product.dto.res

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

//@NoArgsConstructor
class ProductPageResDto(
    val productCode: String,
    val productName: String,
    val originalFileName: String,
    val saveFilename: String
) {
}