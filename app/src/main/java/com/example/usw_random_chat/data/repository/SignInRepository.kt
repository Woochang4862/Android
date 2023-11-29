package com.example.usw_random_chat.data.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface SignInRepository {
    suspend fun signin(param : UserDTO) : UserDTO
}