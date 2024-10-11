package com.example.routee_commerce.data.repositories.auth_repo.data_soures

import com.example.routee_commerce.data.model.request.LoginRequest
import com.example.routee_commerce.data.model.request.RegisterRequest
import com.example.routee_commerce.domain.utils.ApiResult

interface AuthRemoteDataSource {

    suspend fun login(loginRequest: LoginRequest): ApiResult<Boolean>

    suspend fun register(registerRequest: RegisterRequest): ApiResult<Boolean>
}