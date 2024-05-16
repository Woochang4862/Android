package com.example.usw_random_chat.data.dto.response

import com.example.usw_random_chat.data.dto.Token

data class LoginResponse(
    val message : String? = "",
    val data : Token = Token("","")
)
