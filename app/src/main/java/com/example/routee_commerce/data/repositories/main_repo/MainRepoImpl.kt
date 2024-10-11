package com.example.routee_commerce.data.repositories.main_repo

import com.example.routee_commerce.data.repositories.main_repo.data_sources.MainRemoteDataSource
import com.example.routee_commerce.domain.mappers.CategoryMapper
import com.example.routee_commerce.domain.mappers.ProductMapper
import com.example.routee_commerce.domain.model.Category
import com.example.routee_commerce.domain.model.Product
import com.example.routee_commerce.domain.repos.MainRepo
import com.example.routee_commerce.domain.utils.ApiResult
import com.example.routee_commerce.domain.utils.Errors
import com.example.routee_commerce.utils.InternetConnectionChecker
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val categoryMapper: CategoryMapper,
    private val productMapper: ProductMapper,
    private val mainRemoteDataSource: MainRemoteDataSource
) : MainRepo {
    override suspend fun getProducts(): ApiResult<List<Product>> {
        return if (InternetConnectionChecker.isOnline()) {
            when (val result = mainRemoteDataSource.getProducts()) {
                is ApiResult.Error -> ApiResult.Error(result.error)
                is ApiResult.Success -> {
                    ApiResult.Success(result.data.data!!.map {
                        productMapper.toProduct(it)
                    })
                }
            }
        } else {
            ApiResult.Error(Errors.NetworkError)
        }
    }

    override suspend fun getCategories(): ApiResult<List<Category>> {
        return if (InternetConnectionChecker.isOnline()) {
            when (val result = mainRemoteDataSource.getCategories()) {
                is ApiResult.Error -> ApiResult.Error(result.error)
                is ApiResult.Success -> {
                    ApiResult.Success(result.data.data!!.map {
                        categoryMapper.toCategory(it)
                    })
                }
            }
        } else {
            ApiResult.Error(Errors.NetworkError)
        }
    }
}