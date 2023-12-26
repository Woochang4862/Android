package com.example.usw_random_chat.data.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface UserModifyRepository {

    suspend fun PwChange(param : UserDTO) : UserDTO
}