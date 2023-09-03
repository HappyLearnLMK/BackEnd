package com.backend.product.infrastructure.entity

import com.backend.product.domain.Category
import jakarta.persistence.*

@Entity
@Table(name = "CATEGORY")
class CategoryEntity(

    var middleCategory: String,

    var mainCategory: String,

    @Id
    @Column(name = "CATEGORY_CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var categoryCode: Long? = null

) {

    companion object{
        fun from(category: Category): CategoryEntity {
            return CategoryEntity(category.middleCategory, category.mainCategory, categoryCode = category.categoryCode)
        }
    }

    fun to():Category{
        return Category(this.middleCategory, this.mainCategory, this.categoryCode)
    }
}