package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.dto.response.ResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface SignUpApiService {
    @POST("open/member/sign-up") // 회원가입 인증 메일 전송
    @Headers("content-type: application/json")
    suspend fun registerSignUp(@Body jsonpath: UserDTO) : Response<ResponseDTO>
    @POST("open/member/check-duplicate-account") // 아이디 중복확인
    suspend fun registerIdDoubleCheck(@Body jsonpath: UserDTO) : Response<UserDTO>

    @GET("open/member/confirm-email?uuid={uuid}") // 이메일 인증확인
    @Headers("content-type: application/json")
    suspend fun checkAuthEmail(@Path("uuid") uuid : String): Response<UserDTO>

    @POST("open/member/reconfirm-email") // 이메일 재인증
    @Headers("content-type: application/json")
    suspend fun registerAuthEmail(@Body jsonpath: UserDTO): Response<UserDTO>

    @POST("open/member/check-duplicate-email") // 이메일 중복 확인
    @Headers("content-type: application/json")
    suspend fun registerEmailDoubleCheck(@Body jsonpath: UserDTO): Response<UserDTO>

    @POST("open/member/check-duplicate-nickname-signUp") // 닉네임 중복확인
    @Headers("content-type: application/json")
    suspend fun registerCheckSignUpNickName(@Body jsonpath: UserDTO): Response<UserDTO>
}