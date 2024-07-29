package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.dto.response.ProfileResponseDTO
import com.example.usw_random_chat.data.dto.response.ResponseDTO
import com.example.usw_random_chat.data.dto.response.SignUpFinishDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileApiService {
    @PATCH("secure/profile/update-my-profile") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun setProfile(@Body jsonpath: ProfileDTO) : Response<ProfileResponseDTO>

    @GET("secure/profile/get-my-profile") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun getProfile(@Header("authorization") jsonpath: String ) : Response<ProfileResponseDTO>

    @GET("secure/profile/get-other-profile") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun getYourProfile(@Query("targetAccount") param : String) : Response<ProfileResponseDTO>

    @POST("/secure/member/sign-out") // 매칭하기
    suspend fun logout(@Header("Authorization") refreshToken : String) : Response<ResponseDTO>

    @DELETE("secure/member/withdraw") // 회원탈퇴
    suspend fun deleteMember(@Header("Authorization") accessToken: String): Response<SignUpFinishDTO>

}