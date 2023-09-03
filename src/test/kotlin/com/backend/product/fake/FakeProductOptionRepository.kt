package com.backend.product.fake

import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.service.port.ProductOptionRepository
import java.util.concurrent.atomic.AtomicLong

class FakeProductOptionRepository(
    private val generatedId: AtomicLong = AtomicLong(0L),
    private val data: MutableList<ProductOption> = mutableListOf()
) : ProductOptionRepository {
    override fun save(productOption: ProductOption): ProductOption {
        return if (productOption.productOptionSeq == null || productOption.productOptionSeq == 0L) {
            val newProductOption = ProductOption(
                productOption.optionName,
                productOption.optionValue,
                productOption.currentQuantity,
                productOption.product,
                generatedId.incrementAndGet()
            )
            data.add(newProductOption)
            newProductOption
        } else {
            data.removeIf { it.productOptionSeq == productOption.productOptionSeq }
            data.add(productOption)
            productOption
        }
    }

    override fun saveAll(productOptions: List<ProductOption>): List<ProductOption> {
        val productOptionList = mutableListOf<ProductOption>()
        for (productOption in productOptions) {
            productOptionList.add(save(productOption))
        }
        return productOptionList
    }

    override fun findAllByProduct(product: Product): List<ProductOption> {
        return data.filter { it.product.id == product.id }
    }

    override fun removeAll() {
    }

}
