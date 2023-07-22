package com.backend

import com.backend.product.domain.Category
import com.backend.product.repository.CategoryRepository
import com.backend.user.domain.User
import com.backend.user.domain.UserType
import com.backend.user.repository.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class Init (
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @PostConstruct
    fun dataInit() {
        val category = Category("outer", "cloths")
        val user = User(
            "ADMIN", LocalDate.now(), "010-0000-0000", LocalDate.now(),
            'F', UserType.ADMIN, "ADMIN", passwordEncoder.encode("admin"), "Admin"
        )
        categoryRepository.save(category)
        userRepository.save(user)
    }
}