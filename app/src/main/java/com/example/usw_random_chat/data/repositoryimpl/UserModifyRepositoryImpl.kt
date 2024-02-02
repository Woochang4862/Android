package com.example.usw_random_chat.data.repositoryimpl

import android.util.Log
import com.example.usw_random_chat.data.api.UserModifyApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.UserModifyRepository
import javax.inject.Inject

class UserModifyRepositoryImpl @Inject constructor(
    private val userModifyApiService: UserModifyApiService
    ) : UserModifyRepository {

    override suspend fun changePW(param: UserDTO): UserDTO {
        val response = userModifyApiService.registerPwChange(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun postAuthCode(param: UserDTO): UserDTO {
        val response = userModifyApiService.postAuthCode(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun checkAuthCode(param: UserDTO): UserDTO {
        val response = userModifyApiService.checkAuthCode(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun postEmail(param: UserDTO): UserDTO {
        val response = userModifyApiService.postEmail(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun postCheckEmail(param: UserDTO) : Int{
        val response = userModifyApiService.RegisterPostCheckEmail(param)

        return if (response.isSuccessful) {
            response.code()
        } else {
            Log.d("아이디 중복 확인 실패",response.body().toString())
            response.code()
        }
    }
}