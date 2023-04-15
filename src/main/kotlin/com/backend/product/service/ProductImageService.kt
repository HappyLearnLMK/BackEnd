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
    fun saveImage(uploadFile: UploadFile?, product: Product) {
        val productImage = ProductImage(uploadFile!!.storeFIleName, uploadFile.uploadFileName, product)
        productImageRepository.save(productImage)
    }
}