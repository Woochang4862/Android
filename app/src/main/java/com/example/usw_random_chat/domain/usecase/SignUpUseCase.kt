package com.example.usw_random_chat.domain.usecase

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.SignUpRepository

class SignUpUseCase(private val signUpRepository: SignUpRepository) {


    suspend fun signUp(param : UserDTO) : UserDTO {
        return signUpRepository.signup(param)
    }

    suspend fun idDoubleCheck(param : UserDTO) : UserDTO {
        return signUpRepository.idDoubleCheck(param)
    }

    suspend fun authEmail(param : UserDTO) : UserDTO {
        return signUpRepository.authEmail(param)
    }

    suspend fun checkAuthEmail(param : UserDTO) : UserDTO {
        return signUpRepository.authEmail(param)
    }
}