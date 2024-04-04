package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.response.ResponseDTO
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ChatApiService {
    @POST("match/in") // 매칭하기
    suspend fun matching(@Header("Authorization") accessToken : String) : Response<ResponseDTO>

    @GET("profile/get-profile?targetAccount=") // 상대프로필 가져오기
    suspend fun get(@Header("Authorization") accessToken : String) : Response<ResponseDTO>

    @DELETE("match/cancel") // 매칭 취소
    suspend fun cancelMatching(@Header("Authorization") accessToken : String) : Response<ResponseDTO>

    @PATCH("match/out/:room-id") // 매칭룸 나가기
    suspend fun outMatchingRoom(@Header("Authorization") accessToken : String) : Response<ResponseDTO>
}