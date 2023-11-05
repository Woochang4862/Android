package com.example.usw_random_chat.di

import com.example.usw_random_chat.data.api.Register
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
    fun provideRegister(retrofit: Retrofit): Register =
        retrofit.create(Register::class.java)
}