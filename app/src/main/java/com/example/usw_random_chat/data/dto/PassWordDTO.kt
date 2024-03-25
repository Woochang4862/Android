package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class PassWordDTO(
    @SerializedName("newPassword")
    val newPassword : String = "",
    @SerializedName("confirmNewPassword")
    val confirmNewPassword : String = ""
)
