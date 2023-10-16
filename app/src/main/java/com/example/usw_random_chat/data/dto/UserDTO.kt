package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("memberEmail")
    val memberEmail : String = "",
    @SerializedName("memberPassword")
    val memberPassword : String = "",
    @SerializedName("memberName")
    val memberName : String = ""
)
