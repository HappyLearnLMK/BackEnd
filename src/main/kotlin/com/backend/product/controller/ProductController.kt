package com.backend.product.controller

import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.dto.req.ProductPageReqDto
import com.backend.product.dto.req.ProductSaveReqDto
import com.backend.product.service.ProductImageService
import com.backend.product.service.ProductService
import com.backend.util.ResponseDto
import com.backend.util.file.FileStore
import com.backend.util.file.UploadFile
import jakarta.validation.Valid
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/product")
class ProductController(
    private val productService: ProductService,
    private val productImageService: ProductImageService,
    private val fileStore: FileStore
) {
    @PostMapping("/save_product")
    fun saveProduct(
        @RequestPart(required = false) file: MultipartFile?,
        @RequestPart @Valid productSaveReqDto: ProductSaveReqDto,
        bindingResult: BindingResult,
    ): ResponseEntity<*> {
        val saveProduct = productService.saveProduct(productSaveReqDto, file)
        val storeFile: UploadFile? = fileStore.storeFile(file)
        productImageService.saveImage(storeFile, saveProduct)
        return ResponseEntity(ResponseDto(1, "상품 등록 완료", null), HttpStatus.CREATED)
    }

    @PostMapping("/category")
    fun productPage(@RequestBody productPageReqDto: ProductPageReqDto): ResponseEntity<ResponseDto<*>> {
        val categoryReqDto = CategoryReqDto(productPageReqDto.mainCategory, productPageReqDto.middleCategory)
        val productPage = productService.productPage(productPageReqDto, categoryReqDto)
        return ResponseEntity(ResponseDto(1, "상품 목록 호출 완료", productPage), HttpStatus.OK)
    }

    @GetMapping("/image/{fileName}")
    fun downloadImage(@PathVariable fileName: String): UrlResource {
        return UrlResource("file:${fileStore.getFullPath(fileName)}")
    }
}