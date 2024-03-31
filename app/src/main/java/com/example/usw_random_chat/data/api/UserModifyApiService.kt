package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.PassWordDTO
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.dto.response.ResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserModifyApiService {
    @POST("verification/send-code") // 비밀번호 변경용 인증번호 생성
    @Headers("content-type: application/json")
    suspend fun postCodePwSearch(@Body jsonpath: UserDTO) : Response<ResponseDTO>

    @POST("verification/send-code") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun postAuthCode(@Body jsonpath: UserDTO) : Response<UserDTO>

    @POST("member2/signUp") // 세부 주소
    @Headers("content-type: application/json")
    suspend fun checkAuthCode(@Body jsonpath: UserDTO) : Response<UserDTO>

    @PATCH("verification/update-password?uuid={uuid}") // 비밀번호 변경하기
    @Headers("content-type: application/json")
    suspend fun changePW(@Body jsonpath: PassWordDTO, @Header("uuid") uuid : String) : Response<PassWordDTO>

    @POST("member/check-duplicate-id")
    @Headers("content-type: application/json")
    suspend fun registerPostCheckEmail(@Body jsonpath: UserDTO): Response<UserDTO>


}
