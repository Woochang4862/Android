package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpApiService {
    @POST("member2/signUp") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun registerSignUp(@Body jsonpath: UserDTO) : Response<UserDTO>

    suspend fun registerIdDoubleCheck(@Body jsonpath: UserDTO) : Response<UserDTO>

}