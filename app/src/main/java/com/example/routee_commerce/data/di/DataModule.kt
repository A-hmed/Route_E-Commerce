package com.example.routee_commerce.data.di

import com.example.routee_commerce.data.repositories.auth_repo.AuthRepoImpl
import com.example.routee_commerce.data.repositories.auth_repo.data_soures.AuthRemoteDataSource
import com.example.routee_commerce.data.repositories.auth_repo.data_soures.AuthRemoteDataSourceImpl
import com.example.routee_commerce.data.repositories.cart_repository.CartRepoImpl
import com.example.routee_commerce.data.repositories.cart_repository.data_source.CartRemoteDataSource
import com.example.routee_commerce.data.repositories.cart_repository.data_source.CartRemoteDataSourceImpl
import com.example.routee_commerce.data.utils.local_storage.LocalStorage
import com.example.routee_commerce.data.utils.local_storage.LocalStorageImpl
import com.example.routee_commerce.data.repositories.main_repo.MainRepoImpl
import com.example.routee_commerce.data.repositories.main_repo.data_sources.MainRemoteDataSource
import com.example.routee_commerce.data.repositories.main_repo.data_sources.MainRemoteDataSourceImpl
import com.example.routee_commerce.domain.repos.AuthRepo
import com.example.routee_commerce.domain.repos.CartRepo
import com.example.routee_commerce.domain.repos.MainRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindAuthRemoteDataSource(
        dependcy:
        AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    abstract fun bindAuthRepo(dependcy: AuthRepoImpl): AuthRepo

    @Binds
    abstract fun binMainRemoteDataSource(
        dependcy:
        MainRemoteDataSourceImpl
    ): MainRemoteDataSource

    @Binds
    abstract fun bindMainRepo(dependcy: MainRepoImpl): MainRepo

    @Binds
    abstract fun bindCartDataSource(dependcy: CartRemoteDataSourceImpl): CartRemoteDataSource

    @Binds
    abstract fun bindCartRepo(dependcy: CartRepoImpl): CartRepo
}