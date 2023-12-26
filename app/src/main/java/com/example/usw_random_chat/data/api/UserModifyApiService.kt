package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserModifyApiService {
    @POST("member2/signUp") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun registerPwChange(@Body jsonpath: UserDTO) : Response<UserDTO>

}