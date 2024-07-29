package com.example.usw_random_chat.data.repositoryimpl

import android.util.Log
import com.example.usw_random_chat.data.api.UserModifyApiService
import com.example.usw_random_chat.data.dto.PassWordDTO
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.dto.response.PassWordCodeDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.domain.repository.UserModifyRepository
import javax.inject.Inject

class UserModifyRepositoryImpl @Inject constructor(
    private val userModifyApiService: UserModifyApiService,
    private val tokenSharedPreference: TokenSharedPreference
    ) : UserModifyRepository {

    override suspend fun createPWChangeCode(param: UserDTO): Int {
        val response = userModifyApiService.createPWChangeCode(param)
        val uuid = response.body()?.data
        if (uuid != null) {
            tokenSharedPreference.setUUID("uuid",uuid)
        }
        return response.code()
    }


    override suspend fun checkAuthCode(param: PassWordCodeDTO): Int {
        val response = userModifyApiService.checkAuthCode(tokenSharedPreference.getUUID("uuid",""),param)
        Log.d("uuid",tokenSharedPreference.getUUID("uuid",""))
        return response.code()
    }

    override suspend fun changePW(param: PassWordDTO): Int {
        val response = userModifyApiService.changePW(tokenSharedPreference.getUUID("uuid",""),param)

        if (response.isSuccessful){

            Log.d("changePW",response.body().toString())
            return response.code()
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun findUserID(param: String): Int {
        val response = userModifyApiService.findUserID(param)

        return response.code()
    }



}