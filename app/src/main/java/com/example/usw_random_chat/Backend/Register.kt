package com.example.usw_random_chat.Backend

import com.example.usw_random_chat.DTO.UserDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface Register {
    @POST("/member/save") // 세부 주소
    fun registerSignIn(@Body jsonpath: UserDTO) : Call<UserDTO>

    companion object{
        private const val BASE_URL = "http://localhost:8081" // 공통 주소

        fun create(): Register {

            val gson : Gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Register::class.java)
        }
    }
}