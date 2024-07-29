package com.example.usw_random_chat.data.repositoryimpl

import android.annotation.SuppressLint
import android.util.Log
import com.example.usw_random_chat.data.api.ChatApiService
import com.example.usw_random_chat.data.dto.MessageDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.domain.repository.ChatRepository
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import okhttp3.internal.addHeaderLenient
import org.json.JSONObject
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatApiService: ChatApiService,
    private val tokenSharedPreference: TokenSharedPreference
) : ChatRepository {
    override suspend fun matching(accessToken: String): Int {
        val response  = chatApiService.matching(accessToken)

        return if (response.isSuccessful){
            Log.d("매칭 성공",response.body().toString())
            response.code()
        }
        else{
            Log.d("매칭 Fail",response.body().toString())
            response.code()
        }

    }


}