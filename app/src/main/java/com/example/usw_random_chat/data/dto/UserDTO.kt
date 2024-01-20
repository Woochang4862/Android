package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("password")
    val memberPassword : String = "",
    @SerializedName("memberId")
    val memberID : String = "",
    @SerializedName("token")
    val token: Token = Token("","")
)
