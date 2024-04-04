package com.example.usw_random_chat.domain.repository


interface ChatRepository {

    suspend fun matching(accessToken : String) : Int

    suspend fun get(accessToken : String) : Int

    suspend fun cancelMatching(accessToken : String) : Int

    suspend fun outMatchingRoom(accessToken : String) : Int
    
    suspend fun sendMsg(msg : String, wss : String)

    suspend fun connectStomp()

    suspend fun subscribeStomp(wss : String)

    suspend fun unsubscribeStomp(wss: String)

    suspend fun disconnectStomp()

}