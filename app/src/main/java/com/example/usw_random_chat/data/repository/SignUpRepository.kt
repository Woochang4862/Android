package com.example.usw_random_chat.data.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface SignUpRepository {

    suspend fun signup(param : UserDTO) : Int

    suspend fun idDoubleCheck(param : UserDTO) : UserDTO

    suspend fun authEmail(param : UserDTO) : Int

    suspend fun checkAuthEmail(param : UserDTO) : Int

    suspend fun checkSignUpId(param : UserDTO) : Int
}