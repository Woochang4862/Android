package com.example.usw_random_chat.data.repositoryimpl

import android.annotation.SuppressLint
import android.util.Log
import com.example.usw_random_chat.data.repository.ChatRepository
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient

class ChatRepositoryImpl() : ChatRepository {

    private lateinit var stompConnection : Disposable

    private val server= "sd"
    private val client = OkHttpClient()
    private val TAG = "STOMP"

    private val stomp = StompClient(client,1000L).apply { this@apply.url = server }

    @SuppressLint("CheckResult")
    override suspend fun sendMsg(msg: String, wss: String) {
        stomp.send(wss,msg).subscribe{
            if (it){
                Log.d(TAG,"send Succeess : $msg")
            }
            else{
                Log.d(TAG,"send Fail : $msg")
            }
        }
    }

    override suspend fun connectStomp() {
        stompConnection = stomp.connect().subscribe(){
            when(it.type){
                Event.Type.OPENED -> {
                    Log.d(TAG,"stomp connect success")
                }
                Event.Type.CLOSED -> {
                    Log.d(TAG,"stomp close")
                }
                Event.Type.ERROR -> {
                    Log.d(TAG,"stomp connect fail")
                }
                else -> {}
            }
        }
    }

    @SuppressLint("CheckResult")
    override suspend fun subscribeStomp(wss: String) {
        stomp.join(wss).subscribe{ Log.d(TAG,"subscribe Success") }
    }

    override suspend fun unsubscribeStomp(wss: String) {
        stomp.join(wss).subscribe{ Log.d(TAG,"Unsubscribe Success") }.isDisposed
    }

    override suspend fun disconnectStomp() {
        stompConnection.isDisposed
    }
}