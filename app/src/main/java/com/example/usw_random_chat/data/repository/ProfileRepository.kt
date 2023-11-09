package com.example.usw_random_chat.data.repository

import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.dto.UserDTO

interface ProfileRepository {
    suspend fun setProfile(param : ProfileDTO) : ProfileDTO
}