package com.example.usw_random_chat.data.repositoryimpl

import com.example.usw_random_chat.data.api.ProfileApiService
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.dto.response.ProfileResponseDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApiService: ProfileApiService,
    private val tokenSharedPreference: TokenSharedPreference
) : ProfileRepository {

    override suspend fun setProfile(param: ProfileDTO): ProfileDTO {
        val response = profileApiService.setProfile(param)

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Fail!!")
        }
    }

    override suspend fun getProfile(): ProfileResponseDTO {
        val response = profileApiService.getProfile(tokenSharedPreference.getToken("accessToken",""))

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Fail!!")
        }
    }

}