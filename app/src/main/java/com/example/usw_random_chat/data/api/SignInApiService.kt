package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.TempDTO
import com.example.usw_random_chat.data.dto.Token
import com.example.usw_random_chat.data.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface SignInApiService {
    @POST("member/sign-in") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun registerSignIn(@Body jsonpath: UserDTO) : Response<TempDTO>

    @POST("member/auto-sign-in")
    suspend fun autoSignIn() : Response<Token>
}
