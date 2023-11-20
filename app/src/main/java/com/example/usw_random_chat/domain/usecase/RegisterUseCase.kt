package com.example.usw_random_chat.domain.usecase

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.RegisterRepository

class RegisterUseCase(private val registerRepository: RegisterRepository) {
    suspend fun excute(param : UserDTO) : UserDTO {
        return registerRepository.signin(param)
    }

    suspend fun signUp(param : UserDTO) : UserDTO {
        return registerRepository.signup(param)
    }

    suspend fun idDoubleCheck(param : UserDTO) : UserDTO{
        return registerRepository.idDoubleCheck(param)
    }

}