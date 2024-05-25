package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.dto.response.ProfileResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ProfileApiService {
    @PATCH("secure/profile/update-my-profile") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun setProfile(@Body jsonpath: ProfileDTO) : Response<ProfileDTO>

    @PATCH("secure/profile/get-my-profile") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun getProfile(@Header("authorization") jsonpath: String ) : Response<ProfileResponseDTO>


}