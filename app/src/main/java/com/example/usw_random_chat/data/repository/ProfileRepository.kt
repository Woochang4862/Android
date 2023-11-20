package com.example.usw_random_chat.data.repository

import com.example.usw_random_chat.data.dto.ProfileDTO

interface ProfileRepository {
    suspend fun setProfile(param : ProfileDTO) : ProfileDTO
}