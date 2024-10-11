package com.example.routee_commerce.data.di

import android.content.Context
import android.util.Log
import com.example.routee_commerce.data.utils.api.ApiManager
import com.example.routee_commerce.data.utils.api.WebServices
import com.example.routee_commerce.data.utils.local_storage.LocalStorage
import com.example.routee_commerce.data.utils.local_storage.LocalStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    fun provideLocalStorage(@ApplicationContext context: Context): LocalStorage {
        return LocalStorageImpl(context)
    }

}
