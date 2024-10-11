package com.example.routee_commerce.data.repositories.cart_repository

import com.example.routee_commerce.data.repositories.cart_repository.data_source.CartRemoteDataSource
import com.example.routee_commerce.domain.mappers.CartMapper
import com.example.routee_commerce.domain.model.Cart
import com.example.routee_commerce.domain.repos.CartRepo
import com.example.routee_commerce.domain.utils.ApiResult
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val cartRemoteDataSource: CartRemoteDataSource,
    private val cartMapper: CartMapper
) : CartRepo {
    override suspend fun getCart(): ApiResult<Cart> {
        return when (val result = cartRemoteDataSource.getCart()) {
            is ApiResult.Error -> {
                ApiResult.Error(result.error)
            }

            is ApiResult.Success -> {
                ApiResult.Success(cartMapper.toCart(result.data.data!!))
            }
        }
    }

    override suspend fun addCartEntry(productId: String): ApiResult<Cart> {
        return when (val result = cartRemoteDataSource.addCartEntry(productId)) {
            is ApiResult.Error -> {
                ApiResult.Error(result.error)
            }

            is ApiResult.Success -> {
                getCart()
            }
        }
    }

    override suspend fun removeCartEntry(productId: String): ApiResult<Cart> {
        return when (val result = cartRemoteDataSource.removeCartEntry(productId)) {
            is ApiResult.Error -> {
                ApiResult.Error(result.error)
            }

            is ApiResult.Success -> {
                ApiResult.Success(cartMapper.toCart(result.data.data!!))
            }
        }
    }

    override suspend fun updateCartEntryAmount(productId: String, count: Int): ApiResult<Cart> {
        return when (val result = cartRemoteDataSource.updateCartEntryAmount(productId, count)) {
            is ApiResult.Error -> {
                ApiResult.Error(result.error)
            }

            is ApiResult.Success -> {
                ApiResult.Success(cartMapper.toCart(result.data.data!!))
            }
        }
    }
}