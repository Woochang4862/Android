package com.example.usw_random_chat.domain.usecase

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.repository.SignUpRepository

class SignUpUseCase(private val signUpRepository: SignUpRepository) {


    suspend fun signUp(param : UserDTO) : Int {
        return signUpRepository.signup(param)
    }

    suspend fun idDoubleCheck(param : UserDTO) : Int {
        return signUpRepository.idDoubleCheck(param)
    }

    suspend fun reAuthEmail(param : UserDTO) : Int {
        return signUpRepository.reAuthEmail(param)
    }
    suspend fun checkAuthEmail() : Int {
        return signUpRepository.checkAuthEmail()
    }


    suspend fun checkDoubleEmail(param : UserDTO) : Int {
        return signUpRepository.emailDoubleCheck(param)
    }

    suspend fun checkSignUpNickName(param : UserDTO) : Int {
        return signUpRepository.nickNameDoubleCheck(param)
    }
}