package com.example.usw_random_chat.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    //@SerializedName("token")
    //val token: Token? = Token("",""),
   // val code : String? = "",
    @SerializedName("memberEmail")
    val memberEmail : String = "",
    @SerializedName("memberPw")
    val memberPassword : String = "",
    @SerializedName("memberId")
    val memberID : String = ""
)
