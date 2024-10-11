package com.example.routee_commerce.domain.utils

sealed class ApiResult<out T> {
    class Success<out T>(val data: T) : ApiResult<T>()
    class Error(val error: Errors) : ApiResult<Nothing>()
}

sealed class Errors(val errorMessage: String) {
    data object NetworkError : Errors("Please check your internet connection")
    data class ApiError(val message: String?) :
        Errors(message ?: "Something went wrong please try again later")

}