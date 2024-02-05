package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("password")
    val memberPassword : String = "",
    @SerializedName("memberId")
    val memberID : String = "",
    @SerializedName("email")
    val email : String = "",
    @SerializedName("nickname")
    val nickname : String = "",
    @SerializedName("token")
    val token: Token = Token("",""),
    @SerializedName("uuid")
    val uuid : String = ""
)
