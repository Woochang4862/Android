package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class TempDTO(
    @SerializedName("id")
    val id : Long,
    @SerializedName("memberId")
    val id2 : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("nickname")
    val nick : String,
    @SerializedName("roles")
    val role : List<Temp>,
    @SerializedName("token")
    val token: Token
)


data class Temp(
    @SerializedName("name")
    val name : String
)