package com.example.usw_random_chat.data.repositoryimpl

import android.util.Log
import com.example.usw_random_chat.data.api.SignUpApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.SignUpRepository
import com.example.usw_random_chat.di.ApiModule
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val signUpApiService: SignUpApiService)  : SignUpRepository {


    override suspend fun signup(param: UserDTO): UserDTO {
        val response = signUpApiService.registerSignUp(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun idDoubleCheck(param: UserDTO) : UserDTO{
        val response = signUpApiService.registerIdDoubleCheck(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun authEmail(param: UserDTO): UserDTO {
        val response = signUpApiService.registerAuthEmail(param)

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Fail!!")
        }
    }
}