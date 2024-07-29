package com.example.usw_random_chat.domain.repository


interface ChatRepository {
    suspend fun matching(accessToken : String) : Int

}