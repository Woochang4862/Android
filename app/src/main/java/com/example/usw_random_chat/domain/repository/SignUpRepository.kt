package com.example.usw_random_chat.domain.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface SignUpRepository {

    suspend fun signup(param : UserDTO) : Int

    suspend fun idDoubleCheck(param : UserDTO) : Int

    suspend fun authEmail(param : UserDTO) : Int

    suspend fun checkAuthEmail(param : UserDTO) : Int

    suspend fun checkSignUpNickName(param : UserDTO) : Int
}