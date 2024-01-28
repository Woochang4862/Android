package com.example.usw_random_chat.data.repository

interface ChatRepository {
    
    suspend fun sendMsg(msg : String, wss : String)

    suspend fun connectStomp()

    suspend fun subscribeStomp(wss : String)

    suspend fun unsubscribeStomp(wss: String)

    suspend fun disconnectStomp()

}