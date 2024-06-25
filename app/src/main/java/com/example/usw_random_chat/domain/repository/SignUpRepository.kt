package com.example.usw_random_chat.domain.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface SignUpRepository {

    suspend fun signup(param : UserDTO) : Int

    suspend fun idDoubleCheck(param : UserDTO) : Int

    suspend fun reAuthEmail(param : UserDTO) : Int

    suspend fun checkAuthEmail() : Int

    suspend fun emailDoubleCheck(param : UserDTO) : Int

    suspend fun nickNameDoubleCheck(param : UserDTO) : Int
}