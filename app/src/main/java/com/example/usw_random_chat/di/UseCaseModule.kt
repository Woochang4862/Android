package com.example.usw_random_chat.di

import com.example.usw_random_chat.data.repository.ProfileRepository
import com.example.usw_random_chat.data.repository.RegisterRepository
import com.example.usw_random_chat.domain.usecase.ProfileUseCase
import com.example.usw_random_chat.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideRegisterUseCase(registerRepository: RegisterRepository): RegisterUseCase {
        return RegisterUseCase(registerRepository)
    }
    @Singleton
    @Provides
    fun provideProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(profileRepository)
    }
}