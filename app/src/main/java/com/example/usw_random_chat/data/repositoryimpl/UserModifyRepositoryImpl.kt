package com.example.usw_random_chat.data.repositoryimpl

import android.util.Log
import com.example.usw_random_chat.data.api.UserModifyApiService
import com.example.usw_random_chat.data.dto.PassWordDTO
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.repository.UserModifyRepository
import javax.inject.Inject

class UserModifyRepositoryImpl @Inject constructor(
    private val userModifyApiService: UserModifyApiService
    ) : UserModifyRepository {

    override suspend fun searchPW(param: UserDTO): Int {
        val response = userModifyApiService.postCodePwSearch(param)

        if (response.isSuccessful){
            Log.d("PW",response.body().toString())
            return response.code()
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun postAuthCode(param: UserDTO): Int {
        val response = userModifyApiService.postAuthCode(param)

        if (response.isSuccessful){
            Log.d("ID",response.body().toString())
            return response.code()
        }else{
            Log.d("ID",response.body().toString())
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

    override suspend fun changePW(param: PassWordDTO): Int {
        val response = userModifyApiService.changePW(param)

        if (response.isSuccessful){

            Log.d("changePW",response.body().toString())
            return response.code()
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