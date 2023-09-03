package com.backend.product.service

import com.backend.product.domain.Product
import com.backend.product.domain.ProductImage
import com.backend.product.service.port.ProductImageRepository
import com.backend.util.file.UploadFile
import org.springframework.stereotype.Service

@Service
class ProductImageService(
    private val productImageRepository: ProductImageRepository
) {

    fun saveAll(uploadFiles: List<UploadFile?>, type: String, product: Product) {
        if (uploadFiles.isEmpty()) {
            return;
        } else {
            val productImage = uploadFiles.map {
                ProductImage(
                    it!!.storeFileName,
                    it.uploadFileName,
                    type,
                    product
                )
            }
            productImageRepository.saveAll(productImage)
        }
    }

    fun getAllImage(product: Product): List<ProductImage> {
        return productImageRepository.findAllByProduct(product)
    }
}