package com.example.usw_random_chat.di

import com.example.usw_random_chat.MainApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "http://43.202.91.160:8080/"
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: AppInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
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
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(AppInterceptor()))
        .addConverterFactory(gsonConverterFactory)
        .build()

    class AppInterceptor : Interceptor {
       @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response {
            val accessToken = MainApplication.prefs.getToken("accessToken", "") // ViewModel에서 지정한 key로 JWT 토큰을 가져온다.
            val newRequest = chain.request()
                .newBuilder()
                .addHeader("authorization", accessToken) // 헤더에 authorization라는 key로 JWT 를 넣어준다.
                .build()
            return chain.proceed(newRequest)
        }
    }

}