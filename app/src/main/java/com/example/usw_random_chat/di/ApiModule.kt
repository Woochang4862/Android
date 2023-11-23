package com.example.usw_random_chat.di

import com.example.usw_random_chat.data.api.ProfileApiService
import com.example.usw_random_chat.data.api.SignInApiService
import com.example.usw_random_chat.data.api.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideSignInApiService(retrofit: Retrofit): SignInApiService =
        retrofit.create(SignInApiService::class.java)

    @Provides
    @Singleton
    fun provideProfileApiService(retrofit: Retrofit): ProfileApiService =
        retrofit.create(ProfileApiService::class.java)

    @Provides
    @Singleton
    fun provideSignUpApiService(retrofit: Retrofit): SignUpApiService =
        retrofit.create(SignUpApiService::class.java)

}