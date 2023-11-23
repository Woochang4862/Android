package com.example.usw_random_chat.di

import com.example.usw_random_chat.data.repository.ProfileRepository
import com.example.usw_random_chat.data.repository.SignInRepository
import com.example.usw_random_chat.data.repository.SignUpRepository
import com.example.usw_random_chat.data.repositoryimpl.ProfileRepositoryImpl
import com.example.usw_random_chat.data.repositoryimpl.SignInRepositoryImpl
import com.example.usw_random_chat.data.repositoryimpl.SignUpRepositoryImpl
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
    abstract fun provideSignInRepository(repositoryImpl: SignInRepositoryImpl): SignInRepository

    @Singleton
    @Binds
    abstract fun provideProfileRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Singleton
    @Binds
    abstract fun provideSignUpRepository(repositoryImpl: SignUpRepositoryImpl): SignUpRepository
}