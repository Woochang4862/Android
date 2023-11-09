package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class ProfileDTO(
    @SerializedName("nickName")
    val nickName : String = "",
    @SerializedName("mbti")
    val mbti : String? = "",
    @SerializedName("selfIntroduce")
    val selfIntroduce : String? = ""
)
