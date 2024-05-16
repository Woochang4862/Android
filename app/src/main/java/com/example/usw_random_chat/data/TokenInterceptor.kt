package com.example.usw_random_chat.data

import android.util.Log
import com.example.usw_random_chat.MainApplication
import com.example.usw_random_chat.data.local.TokenSharedPreference
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class TokenInterceptor @Inject constructor(private val tokenSharedPreference: TokenSharedPreference) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain) : Response {
        val accessToken = tokenSharedPreference.getToken("accessToken", "") // ViewModel에서 지정한 key로 JWT 토큰을 가져온다.
        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer $accessToken") // 헤더에 authorization라는 key로 JWT 를 넣어준다.
            .build()
        return chain.proceed(newRequest)
    }
}