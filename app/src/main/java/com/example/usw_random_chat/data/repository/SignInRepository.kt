package com.example.usw_random_chat.data.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface SignInRepository {
    suspend fun signIn(param : UserDTO) : UserDTO
}
