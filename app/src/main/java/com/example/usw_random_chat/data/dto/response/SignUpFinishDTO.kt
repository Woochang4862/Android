package com.example.usw_random_chat.data.dto.response

import com.google.gson.annotations.SerializedName

data class SignUpFinishDTO(
    @SerializedName("message")
    val message : String? = "",
    @SerializedName("data")
    val data : String = ""
)
