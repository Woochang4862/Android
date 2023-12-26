package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val accessToken : String,
    val refreshToken : String,
    @SerializedName("memberEmail")
    val memberEmail : String = "",
    @SerializedName("memberPW")
    val memberPassword : String = "",
    @SerializedName("memberID")
    val memberID : String = ""
)
