package com.example.usw_random_chat.data.repositoryimpl

import com.example.usw_random_chat.data.api.UserModifyApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.UserModifyRepository
import javax.inject.Inject

class UserModifyRepositoryImpl @Inject constructor(private val UserModifyApiService: UserModifyApiService)  :
    UserModifyRepository {

    override suspend fun PwChange(param: UserDTO): UserDTO {
        val response = UserModifyApiService.registerPwChange(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }
}