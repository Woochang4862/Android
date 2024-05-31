package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class ProfileDTO(
    @SerializedName("nickname")
    var nickName : String = "",
    @SerializedName("mbti")
    var mbti : String = "",
    @SerializedName("intro")
    var selfIntroduce : String? = ""
)
