package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class ProfileDTO(
    @SerializedName("nickName")
    var nickName : String? = "",
    @SerializedName("mbti")
    var mbti : String? = "",
    @SerializedName("selfIntroduce")
    var selfIntroduce : String? = ""
)
