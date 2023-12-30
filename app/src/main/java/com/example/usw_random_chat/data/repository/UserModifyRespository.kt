package com.example.usw_random_chat.data.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface UserModifyRepository {

    suspend fun changePW(param : UserDTO) : UserDTO

    suspend fun postAuthCode(param : UserDTO) : UserDTO

    suspend fun checkAuthCode(param : UserDTO) : UserDTO

    suspend fun postEmail(param : UserDTO) : UserDTO

}