package com.example.usw_random_chat.data.repositoryimpl

import com.example.usw_random_chat.data.api.SignInApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(private val signInApiService: SignInApiService) : SignInRepository {

    override suspend fun signin(param: UserDTO): UserDTO {
        val response = signInApiService.registerSignIn(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }
}