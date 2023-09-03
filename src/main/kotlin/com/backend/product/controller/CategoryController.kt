package com.backend.product.controller

import com.backend.product.dto.req.CategoryReqDto
import com.backend.product.service.CategoryService
import com.backend.util.ResponseDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryController(
    private val categoryService: CategoryService,
) {
    @PostMapping("/save")
    fun saveCategory(@RequestBody @Valid categoryReqDto: CategoryReqDto, bindingResult: BindingResult):
            ResponseEntity<*> {
        categoryService.create(categoryReqDto)
        return ResponseEntity(ResponseDto(1, "category 등록 완료", null), HttpStatus.CREATED)
    }
}