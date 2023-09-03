package com.backend.product.service.impl

import com.backend.product.domain.Category
import com.backend.product.domain.Product
import com.backend.product.domain.ProductOption
import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.req.ProductPageReqDto
import com.backend.product.dto.req.ProductSaveReqDto
import com.backend.product.dto.res.ProductDetailResDto
import com.backend.product.dto.res.ProductImagesResDto
import com.backend.product.dto.res.ProductPageResDto
import com.backend.product.service.CategoryService
import com.backend.product.service.ProductImageService
import com.backend.product.service.ProductOptionService
import com.backend.product.service.ProductService
import com.backend.product.service.port.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductServiceImpl(
    private val productOptionService: ProductOptionService,
    private val productImageService: ProductImageService,
    private val categoryService: CategoryService,
    private val productRepository: ProductRepository,
) : ProductService {

    @Transactional
    override fun saveProduct(productSaveReq: ProductSaveReqDto): Product {

        val category: Category =
            categoryService.getByMainCategoryAndMiddleCategory(
                productSaveReq.mainCategory,
                productSaveReq.middleCategory
            )

        val product = Product.from(productSaveReq, category)
        val saveProduct = productRepository.save(product)

        val productOptions = productSaveReq.productOptionsReqDtoList.map { optionsDto ->
            val productOption = ProductOption.from(optionsDto, saveProduct)
            productOption
        }.toMutableList()

        productOptionService.saveAll(productOptions)

        return saveProduct
    }

    @Transactional
    override fun productCategoryPage(
        productPageReqDto: ProductPageReqDto,
        categoryReqDto: CategoryReqDto
    ): Page<ProductPageResDto>? {
        val pageRequest = PageRequest.of(productPageReqDto.page, productPageReqDto.size)
        return productRepository.productCategoryPage(pageRequest, categoryReqDto)
    }

    @Transactional
    override fun productDetailPage(productCode: String): ProductDetailResDto? {
        val product = productRepository.findByCode(productCode)

        val productOptionResList = productOptionService.getAllOption(product)

        val findAllByProduct = productImageService.getAllImage(product)

        val thumbsImages = findAllByProduct
            .filter { it.type == "THUMBS" }
            .map { ProductImagesResDto(it.originalFileName, it.saveFilename) }

        val detailImages = findAllByProduct
            .filter { it.type == "DETAIL" }
            .map { ProductImagesResDto(it.originalFileName, it.saveFilename) }

        return ProductDetailResDto(
            product.productName,
            product.retailPrice,
            productOptionResList,
            thumbsImages,
            detailImages
        )
    }
}