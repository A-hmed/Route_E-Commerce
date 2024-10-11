package com.example.routee_commerce.data.repositories.main_repo.data_sources

import com.example.routee_commerce.data.utils.api.WebServices
import com.example.routee_commerce.data.model.response.BaseResponse
import com.example.routee_commerce.data.model.response.category.CategoryDM
import com.example.routee_commerce.data.model.response.product.ProductDM
import com.example.routee_commerce.data.utils.safeApiCall
import com.example.routee_commerce.domain.utils.ApiResult
import com.example.routee_commerce.domain.utils.Errors
import com.google.gson.Gson
import javax.inject.Inject

class MainRemoteDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : MainRemoteDataSource {

    override suspend fun getProducts(): ApiResult<BaseResponse<List<ProductDM>>> {
        return safeApiCall {
            webServices.getProducts()
        }
    }

    override suspend fun getCategories(): ApiResult<BaseResponse<List<CategoryDM>>> {
        return safeApiCall {
            webServices.getCategories()
        }
    }
}