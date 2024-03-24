package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserModifyApiService {
    @POST("verification/send-code") // 비밀번호 변경용 인증번호 생성
    @Headers("content-type: application/json")
    suspend fun postCodePwSearch(@Body jsonpath: UserDTO) : Response<UserDTO>

    @POST("verification/send-code") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun postAuthCode(@Body jsonpath: UserDTO) : Response<UserDTO>

    @POST("member2/signUp") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun checkAuthCode(@Body jsonpath: UserDTO) : Response<UserDTO>

    @PATCH("verification/update-password?uuid=") // 비밀번호 변경하기
    @Headers("content-type: application/json")
    suspend fun changePW(@Body jsonpath: UserDTO) : Response<UserDTO>

    @POST("member/check-duplicate-id")
    @Headers("content-type: application/json")
    suspend fun RegisterPostCheckEmail(@Body jsonpath: UserDTO): Response<UserDTO>

}