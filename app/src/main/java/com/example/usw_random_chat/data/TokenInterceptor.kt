package com.example.usw_random_chat.data

import android.util.Log
import com.example.usw_random_chat.MainApplication
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.domain.repository.SignInRepository
import com.example.usw_random_chat.domain.usecase.SignInUseCase
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class TokenInterceptor @Inject constructor(
    private val tokenSharedPreference: TokenSharedPreference,
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain) : Response {
        val accessToken = tokenSharedPreference.getToken("accessToken", "")
        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer $accessToken") // 헤더에 authorization라는 key로 JWT 를 넣어준다.
            .build()

        val response = chain.proceed(newRequest)
        when(response.code){
            //리프레시 토큰을 이용해 새로운 액세스 토큰을 발급 받아야함
            401 ->{

            }
            //새로 로그인을 시도해야함
            403 ->{
                //유저에게 알림 띄우고 기존의 하던 작업 전부 지우고 로그인 화면으로 돌아가야 함
            }
        }

        return response
    }
}