package com.example.usw_random_chat.data.repositoryimpl

import android.annotation.SuppressLint
import android.util.Log
import com.example.usw_random_chat.data.api.ChatApiService
import com.example.usw_random_chat.domain.repository.ChatRepository
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatApiService: ChatApiService
) : ChatRepository {

    private lateinit var stompConnection : Disposable

    private val client = OkHttpClient()
    private val tag = "STOMP"
    private val serverUrl : String = "ws://3.35.83.91:8080/stomp"

    private val stomp = StompClient(client,5000L).apply { this@apply.url = serverUrl }
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

    override suspend fun get(accessToken: String): Int {
        TODO("Not yet implemented")
    }

    override suspend fun cancelMatching(accessToken: String): Int {
        TODO("Not yet implemented")
    }

    override suspend fun outMatchingRoom(accessToken: String): Int {
        TODO("Not yet implemented")
    }

    @SuppressLint("CheckResult")
    override suspend fun sendMsg(msg: String, wss: String) {
        stomp.send(wss,msg).subscribe{
            if (it){
                Log.d(tag,"send Success : $msg")
            }
            else{
                Log.d(tag,"send Fail : $msg")
            }
        }
    }

    override suspend fun connectStomp() {
        stompConnection = stomp.connect().subscribe(){
            when(it.type){
                Event.Type.OPENED -> {
                    Log.d(tag,"stomp connect success")
                }
                Event.Type.CLOSED -> {
                    Log.d(tag,"stomp close")
                }
                Event.Type.ERROR -> {
                    Log.d(tag,"stomp connect fail")
                }
                else -> {}
            }
        }
    }

    @SuppressLint("CheckResult") // 소스 코드 내에서 인자로 검사에 제외할 항목 ID
    override suspend fun subscribeStomp(wss: String) {
        stomp.join(wss).subscribe{ Log.d(tag,"subscribe Success") }
    }

    override suspend fun unsubscribeStomp(wss: String) {
        stomp.join(wss).subscribe{ Log.d(tag,"Unsubscribe Success") }.isDisposed
    }

    override suspend fun disconnectStomp() {
        stompConnection.isDisposed
    }


}