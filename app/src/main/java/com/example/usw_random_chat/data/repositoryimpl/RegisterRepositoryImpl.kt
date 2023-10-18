package com.example.usw_random_chat.data.repositoryimpl

import com.example.usw_random_chat.data.api.Register
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.RegisterRepository

class RegisterRepositoryImpl(private val register: Register) : RegisterRepository {

    override fun signin(param: UserDTO): UserDTO {
        val response = register.registerSignIn(param).execute()

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Faill!!")
        }
    }

}