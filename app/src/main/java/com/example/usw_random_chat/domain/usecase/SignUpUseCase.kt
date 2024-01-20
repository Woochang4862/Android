package com.example.usw_random_chat.domain.usecase

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.SignUpRepository

class SignUpUseCase(private val signUpRepository: SignUpRepository) {


    suspend fun signUp(param : UserDTO) : Int {
        return signUpRepository.signup(param)
    }

    suspend fun idDoubleCheck(param : UserDTO) : UserDTO {
        return signUpRepository.idDoubleCheck(param)
    }

    suspend fun authEmail(param : UserDTO) : Int {
        return signUpRepository.authEmail(param)
    }

    suspend fun checkAuthEmail(param : UserDTO) : Int {
        return signUpRepository.checkAuthEmail(param)
    }

    suspend fun checkSignUpId(param : UserDTO) : Int {
        return signUpRepository.checkSignUpId(param)
    }
}