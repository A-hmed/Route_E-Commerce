package com.example.routee_commerce.data.repositories.cart_repository.data_source

import com.example.routee_commerce.data.model.response.BaseResponse
import com.example.routee_commerce.data.model.response.cart.CartDM
import com.example.routee_commerce.domain.model.Cart
import com.example.routee_commerce.domain.model.Category
import com.example.routee_commerce.domain.model.Product
import com.example.routee_commerce.domain.utils.ApiResult

interface CartRemoteDataSource {

    suspend fun getCart(): ApiResult<BaseResponse<CartDM>>

    suspend fun addCartEntry(productId: String): ApiResult<BaseResponse<Unit>>
    suspend fun removeCartEntry(productId: String): ApiResult<BaseResponse<CartDM>>

    suspend fun updateCartEntryAmount(productId: String, count: Int): ApiResult<BaseResponse<CartDM>>
}