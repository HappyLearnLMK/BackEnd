package com.backend

import com.backend.product.domain.Category
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class Init (
    private val initService: InitService
) {

    @PostConstruct
    fun init(){
        initService.dbCategoryInit()
    }

    @Component
    @Transactional
    class InitService(
        private val em: EntityManager
    ){
        fun dbCategoryInit() {
            val category = Category("outer", "cloths")
            em.persist(category)
        }

    }
}