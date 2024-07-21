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
import retrofit2.http.Query

interface UserModifyApiService {
    @POST("open/member/send-code") // 비밀번호 변경용 인증번호 생성
    @Headers("content-type: application/json")
    suspend fun postCodePwSearch(@Body jsonpath: UserDTO) : Response<ResponseDTO>

    @POST("verification/verify-code") // 인증번호 확인
    @Headers("content-type: application/json")
    suspend fun checkAuthCode(@Body jsonpath: UserDTO) : Response<UserDTO>

    @PATCH("open/member/update-password") // 비밀번호 변경하기
    @Headers("content-type: application/json")
    suspend fun changePW(@Header("uuid") uuid : String, @Body jsonpath: PassWordDTO ) : Response<PassWordDTO>

    @POST("open/member/find-account") // 아이디 찾기 인증메일 전송
    @Headers("content-type: application/json")
    suspend fun findUserID(@Query("email") email : String)


}
