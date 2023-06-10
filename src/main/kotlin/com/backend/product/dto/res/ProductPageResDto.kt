package com.backend.product.dto.res

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@NoArgsConstructor
@Getter
@Setter
class ProductPageResDto(
    var productCode: String,
    var productName: String,
    var originalFileName: String,
    var saveFilename: String
) {
}