package com.example.usw_random_chat.domain.repository


interface ChatRepository {
    suspend fun matching(accessToken : String) : Int

    suspend fun get(accessToken : String) : Int

    suspend fun cancelMatching(accessToken : String) : Int

    suspend fun outMatchingRoom(accessToken : String) : Int

}