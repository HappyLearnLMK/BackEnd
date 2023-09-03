package com.backend.product.infrastructure.entity

import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.dto.req.ProductOptionReqDto
import jakarta.persistence.*

@Entity
@Table(name = "PRODUCT_OPTION")
class ProductOptionEntity(

    @Column
    val optionName: String,
    @Column
    val optionValue: String,
    @Column
    val currentQuantity: Int,

    @ManyToOne
    @JoinColumn(name = "PRODUCT_CODE")
    val product: ProductEntity,

    @Id
    @Column(name = "PRODUCT_OPTION_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productOptionSeq: Long? = null,
) {

    companion object {
        fun from(productOption: ProductOption): ProductOptionEntity {
            return ProductOptionEntity(
                productOption.optionName,
                productOption.optionValue,
                productOption.currentQuantity,
                ProductEntity.from(productOption.product)
            )
        }
    }

    fun to(): ProductOption {
        return ProductOption(optionName, optionValue, currentQuantity, product.to(), productOptionSeq)
    }
}