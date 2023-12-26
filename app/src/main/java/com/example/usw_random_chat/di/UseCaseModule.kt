package com.example.usw_random_chat.di

import com.example.usw_random_chat.data.repository.ProfileRepository
import com.example.usw_random_chat.data.repository.SignInRepository
import com.example.usw_random_chat.data.repository.SignUpRepository
import com.example.usw_random_chat.data.repository.UserModifyRepository
import com.example.usw_random_chat.domain.usecase.ProfileUseCase
import com.example.usw_random_chat.domain.usecase.SignInUseCase
import com.example.usw_random_chat.domain.usecase.SignUpUseCase
import com.example.usw_random_chat.domain.usecase.UserModifyUseCase
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
    fun provideSignInUseCase(signInRepository: SignInRepository): SignInUseCase {
        return SignInUseCase(signInRepository)
    }
    @Singleton
    @Provides
    fun provideProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(profileRepository)
    }

    @Singleton
    @Provides
    fun provideSignUpUseCase(signUpRepository: SignUpRepository): SignUpUseCase {
        return SignUpUseCase(signUpRepository)
    }

    @Singleton
    @Provides
    fun provideUserModifyUseCase(UserModifyRepository: UserModifyRepository): UserModifyUseCase {
        return UserModifyUseCase(UserModifyRepository)
    }
}