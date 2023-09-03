package com.backend.product.fake

import com.backend.product.domain.Category
import com.backend.product.domain.Product
import com.backend.product.domain.ProductImage
import com.backend.product.domain.ProductOption
import com.backend.product.service.port.ProductImageRepository
import java.util.concurrent.atomic.AtomicLong

class FakeProductImageRepository(
    private val generatedId: AtomicLong = AtomicLong(0L),
    private val data: MutableList<ProductImage> = mutableListOf()
) : ProductImageRepository{
    override fun findById(productImageId: Long): ProductImage {
        return data.filter { it.id == productImageId }[0]
    }

    override fun save(productImage: ProductImage): ProductImage {
        return if (productImage.id == null || productImage.id == 0L) {
            val newProductImage = ProductImage(
                productImage.saveFilename,
                productImage.originalFileName,
                productImage.type,
                productImage.product,
                generatedId.incrementAndGet()
            )
            data.add(newProductImage)
            newProductImage
        }else{
            data.removeIf{it.id == productImage.id}
            data.add(productImage)
            productImage
        }
    }

    override fun saveAll(productImages: List<ProductImage>): List<ProductImage> {
        val productImageList = mutableListOf<ProductImage>()
        for (productImage in productImages) {
            productImageList.add(save(productImage))
        }
        return productImageList
    }

    override fun findAllByProduct(product: Product): List<ProductImage> {
        return data.filter { it.product.id  == product.id }
    }

}
