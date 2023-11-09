package com.example.usw_random_chat.di

import com.example.usw_random_chat.data.repository.ProfileRepository
import com.example.usw_random_chat.data.repository.RegisterRepository
import com.example.usw_random_chat.data.repositoryimpl.ProfileRepositoryImpl
import com.example.usw_random_chat.data.repositoryimpl.RegisterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideRegisterRepository(repositoryImpl: RegisterRepositoryImpl): RegisterRepository

    @Singleton
    @Binds
    abstract fun provideProfileRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository
}