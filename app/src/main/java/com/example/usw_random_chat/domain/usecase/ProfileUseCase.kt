package com.example.usw_random_chat.domain.usecase

import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.repository.ProfileRepository

class ProfileUseCase(private val profileRepository: ProfileRepository) {

    suspend fun excute(param : ProfileDTO) : ProfileDTO {
        return profileRepository.setProfile(param)
    }
}