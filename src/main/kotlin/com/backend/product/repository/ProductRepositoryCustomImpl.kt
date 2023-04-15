package com.backend.product.repository

import com.querydsl.jpa.impl.JPAQueryFactory

class ProductRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ProductRepositoryCustom {

    fun test() {
        jpaQueryFactory.select()
            .from()
    }

}