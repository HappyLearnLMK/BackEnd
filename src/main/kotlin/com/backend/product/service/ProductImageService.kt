package com.backend.product.service

import com.backend.product.domain.Product
import com.backend.product.domain.ProductImage
import com.backend.product.repository.ProductImageRepository
import com.backend.util.file.UploadFile
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductImageService(
    private val productImageRepository: ProductImageRepository
) {

    @Transactional
    fun saveImages(uploadFiles: List<UploadFile?>, type: String, product: Product) {
        val productImages = uploadFiles.map {
            ProductImage(
                it!!.storeFileName,
                it.uploadFileName,
                type,
                product
            )
        }

        productImageRepository.saveAll(productImages)

    }
}