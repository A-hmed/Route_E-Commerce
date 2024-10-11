package com.example.routee_commerce.ui.cart

import com.example.routee_commerce.domain.repos.CartRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Singleton
    @Provides
    fun provideSharedViewModel(
        cartRepo: CartRepo
    ): CartViewModel {
        return CartViewModel(cartRepo)
    }
}