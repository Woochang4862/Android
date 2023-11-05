package com.example.usw_random_chat.data.repositoryimpl

import com.example.usw_random_chat.data.api.Register
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val register: Register) : RegisterRepository {

    override suspend fun signin(param: UserDTO): UserDTO {
        val response = register.registerSignIn(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

}