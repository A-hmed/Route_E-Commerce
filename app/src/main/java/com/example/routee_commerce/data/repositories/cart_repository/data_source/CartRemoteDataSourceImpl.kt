package com.example.routee_commerce.data.repositories.cart_repository.data_source

import com.example.routee_commerce.data.model.request.AddCartEntryRequest
import com.example.routee_commerce.data.model.request.UpdateCartEntryRequest
import com.example.routee_commerce.data.model.response.BaseResponse
import com.example.routee_commerce.data.utils.api.WebServices
import com.example.routee_commerce.data.utils.local_storage.LocalStorage
import com.example.routee_commerce.data.model.response.cart.CartDM
import com.example.routee_commerce.data.utils.safeApiCall
import com.example.routee_commerce.domain.utils.ApiResult
import javax.inject.Inject

class CartRemoteDataSourceImpl @Inject constructor(
    private val services: WebServices,
) : CartRemoteDataSource {
    override suspend fun getCart(): ApiResult<BaseResponse<CartDM>> =
        safeApiCall { services.getUserCart() }

    override suspend fun addCartEntry(productId: String): ApiResult<BaseResponse<Unit>> =
        safeApiCall { services.addCartEntry(AddCartEntryRequest(productId)) }

    override suspend fun removeCartEntry(productId: String): ApiResult<BaseResponse<CartDM>> =
        safeApiCall { services.removeCartEntry(productId) }

    override suspend fun updateCartEntryAmount(
        productId: String,
        count: Int
    ): ApiResult<BaseResponse<CartDM>> =
        safeApiCall { services.updateCartItemQuantity(productId, UpdateCartEntryRequest(count)) }
}