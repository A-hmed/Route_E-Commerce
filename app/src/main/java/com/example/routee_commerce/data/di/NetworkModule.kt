package com.example.routee_commerce.data.di

import android.util.Log
import com.example.routee_commerce.data.utils.api.ApiManager
import com.example.routee_commerce.data.utils.api.WebServices
import com.example.routee_commerce.data.utils.local_storage.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }

    @Provides
    fun provideRetroFit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiManager.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor,
                            authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideAuthInterceptor(localStorage: LocalStorage): Interceptor {
        val interceptor =  Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("token", "${localStorage.getToken()}")
                .build()
            return@Interceptor chain.proceed(newRequest)
        }
        return interceptor
    }
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor {
            Log.e("API_CALL", it)
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }


}
