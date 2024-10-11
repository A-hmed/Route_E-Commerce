package com.example.routee_commerce.data.utils.api

import com.example.routee_commerce.data.model.request.AddCartEntryRequest
import com.example.routee_commerce.data.model.request.LoginRequest
import com.example.routee_commerce.data.model.request.RegisterRequest
import com.example.routee_commerce.data.model.request.UpdateCartEntryRequest
import com.example.routee_commerce.data.model.response.AuthResponse
import com.example.routee_commerce.data.model.response.BaseResponse
import com.example.routee_commerce.data.model.response.cart.CartDM
import com.example.routee_commerce.data.model.response.category.CategoryDM
import com.example.routee_commerce.data.model.response.product.ProductDM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface WebServices {

    @POST("/api/v1/auth/signup")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<AuthResponse>

    @POST("/api/v1/auth/signin")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<AuthResponse>

    @GET("/api/v1/products")
    suspend fun getProducts(): Response<BaseResponse<List<ProductDM>>>

    @GET("/api/v1/categories")
    suspend fun getCategories(): Response<BaseResponse<List<CategoryDM>>>

    @GET("api/v1/cart")
    suspend fun getUserCart(): Response<BaseResponse<CartDM>>

    @POST("api/v1/cart")
    suspend fun addCartEntry(@Body addCartEntryRequest: AddCartEntryRequest): Response<BaseResponse<Unit>>

    @DELETE("api/v1/cart/{productId}")
    suspend fun removeCartEntry(
        @Path("productId") productId: String
    ): Response<BaseResponse<CartDM>>

    @PUT("api/v1/cart/{productId}")
    suspend fun updateCartItemQuantity(
        @Path("productId") productId: String,
        @Body updateCartEntryRequest: UpdateCartEntryRequest
    ): Response<BaseResponse<CartDM>>
}