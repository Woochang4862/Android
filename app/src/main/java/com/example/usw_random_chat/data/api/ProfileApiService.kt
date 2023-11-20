package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.ProfileDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProfileApiService {
    @POST("member/??") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun setProfile(@Body jsonpath: ProfileDTO) : Response<ProfileDTO>
}