package com.backend.product.controller

import com.backend.product.dto.req.ProductReqDto
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
        @RequestPart @Valid productReqDto: ProductReqDto,
        @RequestPart(required = false) file: MultipartFile?,
        bindingResult: BindingResult
    ): ResponseEntity<*> {
        val saveProduct = productService.saveProduct(productReqDto, file)
        val storeFile: UploadFile? = fileStore.storeFile(file)
        productImageService.saveImage(storeFile, saveProduct)
        return ResponseEntity(ResponseDto(1, "상품 등록 완료", productReqDto), HttpStatus.OK)
    }

    @GetMapping("/image/{fileName}")
    fun downloadImage(@PathVariable fileName: String): UrlResource {
        return UrlResource("file:${fileStore.getFullPath(fileName)}")
    }
}