package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.response.ResponseDTO
import com.example.usw_random_chat.data.dto.response.SignUpFinishDTO
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ChatApiService {
    @POST("/secure/match/in") // 매칭하기
    suspend fun matching(@Header("Authorization") accessToken: String): Response<ResponseDTO>

}