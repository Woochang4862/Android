package com.example.usw_random_chat.domain.repository

import com.example.usw_random_chat.data.dto.PassWordDTO
import com.example.usw_random_chat.data.dto.UserDTO

interface UserModifyRepository {

    suspend fun createPWChangeCode(param : UserDTO) : Int

    suspend fun checkAuthCode(param : String) : UserDTO

    suspend fun changePW(param : PassWordDTO) : Int

    suspend fun findUserID(param : String) : Int

}