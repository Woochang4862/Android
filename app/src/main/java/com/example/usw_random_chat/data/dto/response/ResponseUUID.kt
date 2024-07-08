package com.example.usw_random_chat.data.dto.response

import com.google.gson.annotations.SerializedName

data class ResponseUUID(
    @SerializedName("message")
    val message : String? = "",
    @SerializedName("data")
    val data : SignUpFinish = SignUpFinish("","")
    )


data class SignUpFinish(
    @SerializedName("uuid")
    val uuid : String? = "",
    @SerializedName("account")
    val account : String? = ""
)