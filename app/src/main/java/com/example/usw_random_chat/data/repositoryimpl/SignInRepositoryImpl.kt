package com.example.usw_random_chat.data.repositoryimpl

import com.example.usw_random_chat.data.api.SignInApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.data.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInApiService: SignInApiService,
    private val tokenSharedPreference: TokenSharedPreference
) : SignInRepository {

    override suspend fun signin(param: UserDTO): UserDTO {
        val response = signInApiService.registerSignIn(param)

        if (response.isSuccessful) {
            val accessToken = response.body()?.accessToken
            val refreshToken = response.body()?.refreshToken
            tokenSharedPreference.setToken("accessToken","token : $accessToken")
            tokenSharedPreference.setToken("refreshToken","token : $refreshToken")
            return response.body()!!
        } else {
            throw Exception("Fail!!")
        }
    }
}