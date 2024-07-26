package com.example.usw_random_chat.data.dto.response

import com.google.gson.annotations.SerializedName

data class PassWordCodeDTO(
    @SerializedName("verificationCode")
    val verificationCode : String = ""
)
