package com.example.usw_random_chat.data.api

import com.example.usw_random_chat.data.dto.UserDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Register {
    @POST("member/save") // 세부 주소
    @Headers("content-type: application/json")
    fun registerSignUp(@Body jsonpath: UserDTO) : Call<UserDTO>

    @POST("member/login") // 세부 주소
    @Headers("content-type: application/json")
    fun registerSignIn(@Body jsonpath: UserDTO) : Call<UserDTO>


    companion object{
        private const val BASE_URL = "http://3.35.83.91:8080/" // 공통 주소

        fun create(): Register {

            val gson : Gson = GsonBuilder().setLenient().create()

            //http통신 로그를 볼 수 있게 해주는 코드
            val client : OkHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.build()

            return Retrofit
                .Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Register::class.java)
        }
    }
}