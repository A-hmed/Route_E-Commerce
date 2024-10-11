package com.example.routee_commerce.domain.repos

import com.example.routee_commerce.domain.model.Category
import com.example.routee_commerce.domain.model.Product
import com.example.routee_commerce.domain.utils.ApiResult

interface MainRepo {

    suspend fun getProducts(): ApiResult<List<Product>>

    suspend fun getCategories(): ApiResult<List<Category>>
}