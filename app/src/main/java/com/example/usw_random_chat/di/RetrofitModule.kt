package com.example.usw_random_chat.di

import android.util.Log
import com.example.usw_random_chat.MainApplication
import com.example.usw_random_chat.data.TokenInterceptor
import com.example.usw_random_chat.data.api.SignInApiService
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.data.repositoryimpl.SignInRepositoryImpl
import com.example.usw_random_chat.domain.repository.SignInRepository
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.CookieManager
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "http://43.202.91.160:8080/"
    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideTokenInterceptor(
        tokenSharedPreference: TokenSharedPreference,
        signInRepository: Lazy<SignInRepository>
    ): TokenInterceptor {
        return TokenInterceptor(tokenSharedPreference, signInRepository)
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        tokenInterceptor: TokenInterceptor
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(tokenInterceptor))
        .addConverterFactory(gsonConverterFactory)
        .build()


}