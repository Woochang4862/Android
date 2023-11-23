package com.example.usw_random_chat.domain.usecase

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.SignInRepository

class SignInUseCase(private val signInRepository: SignInRepository) {
    suspend fun excute(param : UserDTO) : UserDTO {
        return signInRepository.signin(param)
    }


}