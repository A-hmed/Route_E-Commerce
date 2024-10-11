package com.example.routee_commerce.data.repositories.auth_repo.data_soures

import android.util.Log
import com.example.routee_commerce.data.utils.api.WebServices
import com.example.routee_commerce.data.utils.local_storage.LocalStorage
import com.example.routee_commerce.data.model.request.LoginRequest
import com.example.routee_commerce.data.model.request.RegisterRequest
import com.example.routee_commerce.data.model.response.AuthResponse
import com.example.routee_commerce.domain.utils.ApiResult
import com.example.routee_commerce.domain.utils.Errors
import com.google.gson.Gson
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val services: WebServices,
    private val localStorage: LocalStorage
) : AuthRemoteDataSource {
    override suspend fun login(loginRequest: LoginRequest): ApiResult<Boolean> {
        return try {
            val authResponse = services.login(loginRequest)
            if (authResponse.isSuccessful) {
                localStorage.saveUser(authResponse.body()!!.user!!)
                localStorage.saveToken(authResponse.body()!!.token!!)
                ApiResult.Success(true)
            } else {
                val gson = Gson()
                Log.e("register", "errorResponse: ${authResponse.errorBody()?.string()}")
                val errorResponse =
                    gson.fromJson(authResponse.errorBody()?.string(), AuthResponse::class.java)
                ApiResult.Error(Errors.ApiError(errorResponse?.message ?: "Default"))
            }
        } catch (e: Throwable) {
            ApiResult.Error(Errors.ApiError(e.message.toString()))
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): ApiResult<Boolean> {
        return try {
            val authResponse = services.register(registerRequest)
            if (authResponse.isSuccessful) {
                ApiResult.Success(true)
            } else {
                val gson = Gson()
                Log.e("register", "errorResponse: ${authResponse.errorBody()?.string()}")
                val errorResponse =
                    gson.fromJson(authResponse.errorBody()?.string(), AuthResponse::class.java)

                ApiResult.Error(Errors.ApiError(errorResponse?.message ?: "Default"))
            }
        } catch (e: Throwable) {
            ApiResult.Error(Errors.ApiError(e.message.toString()))
        }
    }

}