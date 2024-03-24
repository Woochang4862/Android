package com.example.usw_random_chat.domain.repository

import com.example.usw_random_chat.data.dto.UserDTO

interface UserModifyRepository {

    suspend fun searchPW(param : UserDTO) : Int

    suspend fun postAuthCode(param : UserDTO) : Int

    suspend fun checkAuthCode(param : UserDTO) : UserDTO

    suspend fun changePW(param : UserDTO) : Int

    suspend fun postCheckEmail(param : UserDTO) : Int

}