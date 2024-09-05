package com.example.usw_random_chat.data.dto


import kotlinx.serialization.Serializable

@Serializable
data class MessageDTO(
    val roomId : String = "",
    val sender : String = "",
    val contents : String = "",
    val sendTime : String = "",
)
