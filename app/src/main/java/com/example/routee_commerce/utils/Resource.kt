package com.example.routee_commerce.utils

import com.example.routee_commerce.domain.utils.Errors

sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    class Error(val error: Errors) : Resource<Nothing>()

    data object Loading: Resource<Nothing>()
}
