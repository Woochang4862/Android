package com.example.usw_random_chat.data.repositoryimpl

import android.util.Log
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

    override suspend fun setProfile(param: ProfileDTO) : String {
        val response = profileApiService.setProfile(param)

        return if (response.isSuccessful) {
            Log.d("프로필 전송 성공",response.body().toString())
            response.body()?.message.toString()
        } else {
            Log.d("프로필 전송 실패",response.body().toString())
            response.body()?.message.toString()
        }
    }

    override suspend fun getProfile(): ProfileResponseDTO {
        val response = profileApiService.getProfile(tokenSharedPreference.getToken("accessToken",""))

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            return ProfileResponseDTO("",ProfileDTO("닉네임을 작성해주세요","MBTI를 작성해주세요!","자기소개를 작성해주세요!"))
        }
    }

    override suspend fun getYourProfile(): ProfileResponseDTO {
        val response = profileApiService.getYourProfile("asd")

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            return ProfileResponseDTO("",ProfileDTO("잘못된 닉네임입니다.","잘못된 MBTI입니다.","잘못된 자기소개입니다."))
        }

    }

}