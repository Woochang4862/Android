package com.example.usw_random_chat.domain.usecase

import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.UserModifyRepository

class UserModifyUseCase(private val UserModifyRepository: UserModifyRepository) {

    suspend fun pwChange(param : UserDTO) : UserDTO {
        return UserModifyRepository.PwChange(param)
    }
}