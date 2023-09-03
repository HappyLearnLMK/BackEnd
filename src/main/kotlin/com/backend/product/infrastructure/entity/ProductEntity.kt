package com.backend.product.infrastructure.entity

import com.backend.product.domain.Category
import com.backend.product.domain.Product
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "PRODUCT")
class ProductEntity(

    @Column(name = "PRODUCT_NAME", nullable = false)
    val productName: String,
    @Column(name = "WHOLE_PRICE")
    val wholePrice: Int,
    @Column(name = "RETAIL_PRICE")
    val retailPrice: Int,
    @Column(name = "SALES_YN", nullable = false)
    val salesYn: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_CODE")
    val category: CategoryEntity,

    @Column(name = "PRODUCT_CODE", unique = true)
    var productCode: String,

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {

    companion object {
        fun from(product: Product): ProductEntity {
            return ProductEntity(
                productName = product.productName,
                wholePrice = product.wholePrice,
                retailPrice = product.retailPrice,
                salesYn = product.salesYn,
                category = CategoryEntity.from(product.category),
                productCode = UUID.randomUUID().toString()
            )
        }
    }

    fun to(): Product {
        return Product(
            this.productName, this.wholePrice, this.retailPrice, this.salesYn,
            this.category.to(),
            this.productCode,
            this.id
        )
    }
}