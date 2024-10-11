package com.example.routee_commerce.data.utils

import com.example.routee_commerce.data.model.response.BaseResponse
import com.example.routee_commerce.domain.utils.ApiResult
import com.example.routee_commerce.domain.utils.Errors
import com.google.gson.Gson
import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = apiCall.invoke()
        if (response.isSuccessful && response.body() != null) {
            ApiResult.Success(response.body()!!)
        } else {
            val gson = Gson()
            val errorResponse =
                gson.fromJson(response.errorBody()?.string(), BaseResponse::class.java)

            ApiResult.Error(Errors.ApiError(errorResponse?.message))
        }
    } catch (t: Throwable) {
        ApiResult.Error(Errors.ApiError(t.message))
    }
}