package com.example.routee_commerce.data.repositories.auth_repo

import com.example.routee_commerce.data.repositories.auth_repo.data_soures.AuthRemoteDataSource
import com.example.routee_commerce.data.model.request.LoginRequest
import com.example.routee_commerce.data.model.request.RegisterRequest
import com.example.routee_commerce.domain.repos.AuthRepo
import com.example.routee_commerce.domain.utils.ApiResult
import com.example.routee_commerce.domain.utils.Errors
import com.example.routee_commerce.utils.InternetConnectionChecker
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authRemoteDataSource:
    AuthRemoteDataSource
) : AuthRepo {
    override suspend fun login(loginRequest: LoginRequest): ApiResult<Boolean> {
        return if (InternetConnectionChecker.isOnline()) {
            authRemoteDataSource.login(loginRequest)
        } else {
            ApiResult.Error(Errors.NetworkError)
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): ApiResult<Boolean> {
        return if (InternetConnectionChecker.isOnline()) {
            authRemoteDataSource.register(registerRequest)
        } else {
            ApiResult.Error(Errors.NetworkError)
        }
    }
}