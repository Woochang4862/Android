package com.example.usw_random_chat.data.usecase

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.RegisterRepository

class RegisterUseCase(private val registerRepository: RegisterRepository) {
    fun excute(param : UserDTO) : UserDTO {
        return registerRepository.signin(param)
    }
}