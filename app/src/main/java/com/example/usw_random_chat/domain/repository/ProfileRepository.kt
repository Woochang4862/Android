package com.example.usw_random_chat.domain.repository

import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.dto.response.ProfileResponseDTO

interface ProfileRepository {
    suspend fun setProfile(param : ProfileDTO) : String

    suspend fun getProfile() : ProfileResponseDTO

    suspend fun getYourProfile(targetAccount : String) : ProfileResponseDTO
}