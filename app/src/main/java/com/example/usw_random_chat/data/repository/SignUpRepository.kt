package com.example.usw_random_chat.data.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface SignUpRepository {

    suspend fun signup(param : UserDTO) : UserDTO

    suspend fun idDoubleCheck(param : UserDTO) : UserDTO
}