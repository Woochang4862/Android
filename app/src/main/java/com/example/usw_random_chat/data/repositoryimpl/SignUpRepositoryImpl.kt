package com.example.usw_random_chat.data.repositoryimpl

import com.example.usw_random_chat.data.api.SignUpApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.SignUpRepository
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

}