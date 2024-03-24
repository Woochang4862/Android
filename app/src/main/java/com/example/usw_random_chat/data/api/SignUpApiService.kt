package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpApiService {
    @POST("member/sign-up")
    @Headers("content-type: application/json")
    suspend fun registerSignUp(@Body jsonpath: UserDTO) : Response<UserDTO>
    @POST("member/check-duplicate-account")
    suspend fun registerIdDoubleCheck(@Body jsonpath: UserDTO) : Response<UserDTO>

    @POST("member/sign-up")
    @Headers("content-type: application/json")
    suspend fun registerAuthEmail(@Body jsonpath: UserDTO): Response<UserDTO>

    @POST("member/reconfirm-email")
    @Headers("content-type: application/json")
    suspend fun registerCheckAuthEmail(@Body jsonpath: UserDTO): Response<UserDTO>

    @POST("member/check-duplicate-nickname-signUp")
    @Headers("content-type: application/json")
    suspend fun registerCheckSignUpNickName(@Body jsonpath: UserDTO): Response<UserDTO>
}