package com.example.usw_random_chat.data.dto.response

import com.example.usw_random_chat.data.dto.Token
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message : String? = "",
    @SerializedName("data")
    val data : Token = Token("","")
)
