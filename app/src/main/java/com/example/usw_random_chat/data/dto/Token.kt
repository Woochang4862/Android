package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("accessToken")
    val accessToken: String? = "",
    @SerializedName("refreshToken")
    val refreshToken: String? = ""
)
