package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.dto.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInApiService {
    @POST("member/sign-in") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun registerSignIn(@Body jsonpath: UserDTO) : Response<UserDTO>

    @POST("member/auto-sign-in")
    suspend fun autoSignIn(@Body jsonpath: Token ) : Response<Token>
}
