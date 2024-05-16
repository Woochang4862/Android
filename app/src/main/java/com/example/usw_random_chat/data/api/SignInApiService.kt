package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.dto.Token
import com.example.usw_random_chat.data.dto.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInApiService {
    @POST("open/member/sign-in") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun registerSignIn(@Body jsonpath: UserDTO) : Response<LoginResponse>

    @POST("secure/jwt/reissue-token")
    @Headers("content-type: application/json")
    suspend fun autoSignIn(@Header("authorization") jsonpath: String ) : Response<LoginResponse>
}
