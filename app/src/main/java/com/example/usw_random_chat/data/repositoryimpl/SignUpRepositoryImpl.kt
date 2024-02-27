package com.example.usw_random_chat.data.repositoryimpl

import android.util.Log
import com.example.usw_random_chat.data.api.SignUpApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpApiService: SignUpApiService
)  : SignUpRepository {


    override suspend fun signup(param: UserDTO): Int {
        val response = signUpApiService.registerSignUp(param)

        return if (response.isSuccessful) {
            response.code()
        } else {
            Log.d("회원 가입 실패",response.body().toString())
            response.code()
        }
    }

    override suspend fun idDoubleCheck(param: UserDTO) : Int{
        val response = signUpApiService.registerIdDoubleCheck(param)

        return if (response.isSuccessful) {
            response.code()
        } else {
            Log.d("아이디 중복 확인 실패",response.body().toString())
            response.code()
        }
    }

    override suspend fun authEmail(param: UserDTO): Int {
        val response = signUpApiService.registerAuthEmail(param)

        return if (response.isSuccessful) {
            response.code()
        } else {
            Log.d("이메일 전송 실패",response.body().toString())
            response.code()
        }
    }

    override suspend fun checkAuthEmail(param: UserDTO): Int {
        val response = signUpApiService.registerCheckAuthEmail(param)

        return if (response.isSuccessful) {
            response.code()
        } else {
            Log.d("이메일 인증 실패",response.body().toString())
            response.code()
        }
    }

    override suspend fun checkSignUpNickName(param: UserDTO): Int {
        val response = signUpApiService.registerCheckSignUpNickName(param)

        return if (response.isSuccessful) {
            response.code()
        } else {
            Log.d("닉네임 중복 확인 실패.",response.body().toString())
            response.code()
        }
    }
}