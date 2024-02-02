package com.example.usw_random_chat.domain.usecase

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.UserModifyRepository

class UserModifyUseCase(private val userModifyRepository: UserModifyRepository) {

    suspend fun pwChange(param : UserDTO) : UserDTO {
        return userModifyRepository.changePW(param)
    }

    suspend fun postAuthCode(param : UserDTO) : UserDTO {
        return userModifyRepository.postAuthCode(param)
    }

    suspend fun checkAuthCode(param : UserDTO) : UserDTO {
        return userModifyRepository.checkAuthCode(param)
    }

    suspend fun postEmail(param : UserDTO) : UserDTO {
        return userModifyRepository.postEmail(param)
    }

    suspend fun postCheckEmail(param : UserDTO) : Int {
        return userModifyRepository.postCheckEmail(param)
    }
}