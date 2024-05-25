package com.example.usw_random_chat.data.dto.response

import com.example.usw_random_chat.data.dto.ProfileDTO

data class ProfileResponseDTO(
    val message : String? = "",
    val data : ProfileDTO = ProfileDTO("","","")
)
