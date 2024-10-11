package com.example.routee_commerce.domain.repos

import com.example.routee_commerce.domain.model.Cart
import com.example.routee_commerce.domain.model.Category
import com.example.routee_commerce.domain.model.Product
import com.example.routee_commerce.domain.utils.ApiResult

interface CartRepo {

    suspend fun getCart(): ApiResult<Cart>

    suspend fun addCartEntry(productId: String): ApiResult<Cart>
    suspend fun removeCartEntry(productId: String): ApiResult<Cart>

    suspend fun updateCartEntryAmount(productId: String, count: Int): ApiResult<Cart>
}