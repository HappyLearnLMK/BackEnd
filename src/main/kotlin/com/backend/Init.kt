package com.backend

import com.backend.product.infrastructure.entity.CategoryEntity
import com.backend.product.infrastructure.entity.ProductEntity
import com.backend.product.infrastructure.repository.CategoryJpaRepository
import com.backend.product.infrastructure.repository.ProductJpaRepository
import com.backend.user.domain.User
import com.backend.user.domain.UserType
import com.backend.user.repository.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class Init(
    private val categoryRepository: CategoryJpaRepository,
    private val productJpaRepository: ProductJpaRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @PostConstruct
    fun dataInit() {
        val category = CategoryEntity("outer", "cloths")
        val user = User(
            "ADMIN", LocalDate.now(), "010-0000-0000", LocalDate.now(),
            'F', UserType.ADMIN, "ADMIN@naver.com", passwordEncoder.encode("admin"), "Admin"
        )
        val saveCategory = categoryRepository.save(category)
        val product =
            ProductEntity("test", 100, 10, true, saveCategory, UUID.randomUUID().toString())
        productJpaRepository.save(product)
        userRepository.save(user)
    }
}