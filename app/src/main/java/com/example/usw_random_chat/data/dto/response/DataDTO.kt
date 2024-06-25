package com.example.usw_random_chat.data.dto.response

import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("uuid")
    val uuid : String = "",
    @SerializedName("nickname")
    val nickName : String = "",
    @SerializedName("mbti")
    val mbti : String? = "",
    @SerializedName("intro")
    val selfIntroduce : String? = ""
)
