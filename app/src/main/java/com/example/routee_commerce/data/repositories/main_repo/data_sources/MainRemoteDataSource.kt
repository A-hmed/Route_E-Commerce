package com.example.routee_commerce.data.repositories.main_repo.data_sources

import com.example.routee_commerce.data.model.response.BaseResponse
import com.example.routee_commerce.data.model.response.category.CategoryDM
import com.example.routee_commerce.data.model.response.product.ProductDM
import com.example.routee_commerce.domain.utils.ApiResult

interface MainRemoteDataSource {

    suspend fun getProducts(): ApiResult<BaseResponse<List<ProductDM>>>

    suspend fun getCategories(): ApiResult<BaseResponse<List<CategoryDM>>>
}