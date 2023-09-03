package com.backend.product.infrastructure.entity

import com.backend.product.domain.ProductImage
import com.backend.product.domain.ProductOption
import jakarta.persistence.*

@Entity
@Table(name = "PRODUCT_IMAGE")
class ProductImageEntity(

    @Column
    val saveFilename: String,

    @Column
    val originalFileName: String,

    @Column
    val type: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_CODE")
    val product: ProductEntity,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {
    companion object {
        fun from(productImage: ProductImage): ProductImageEntity {
            return ProductImageEntity(
                saveFilename = productImage.saveFilename,
                originalFileName = productImage.originalFileName,
                type = productImage.type,
                product = ProductEntity.from(productImage.product)
            )
        }
    }

    fun to(): ProductImage {
        return ProductImage(saveFilename, originalFileName, type, product.to())
    }

}