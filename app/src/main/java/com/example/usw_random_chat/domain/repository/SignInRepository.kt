package com.example.usw_random_chat.domain.repository

import com.example.usw_random_chat.data.dto.Token
import com.example.usw_random_chat.data.dto.UserDTO

interface SignInRepository {
    suspend fun signIn(param : UserDTO) : Int

    suspend fun autoSignIn(token: Token) : Int
}
