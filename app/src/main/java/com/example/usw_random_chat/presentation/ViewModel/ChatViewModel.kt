package com.example.usw_random_chat.presentation.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.MessageDTO
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.domain.repository.ChatRepository
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val tokenSharedPreference: TokenSharedPreference
) : ViewModel() {
    private lateinit var stompConnection : Disposable
    private val client = OkHttpClient.Builder().build()
    private val tag = "STOMP"
    private val serverUrl : String = "ws://43.202.91.160:8080/stomp"
    private val stomp = StompClient(client,5000L).apply { this@apply.url = serverUrl }

    private val _chatList = mutableStateListOf<MessageDTO>()
    private val _msg = mutableStateOf("")
    private val _profileDialog = mutableStateOf(false)
    private val _reportDialog = mutableStateOf(false)
    private val _exitDialog = mutableStateOf(false)
    private val _userProfile : ProfileDTO = ProfileDTO()

    val chatList = _chatList
    val msg : State<String> = _msg
    val profileDialog : State<Boolean> = _profileDialog
    val exitDialog : State<Boolean> = _exitDialog
    val reportDialog : State<Boolean> = _reportDialog
    val userProfile : ProfileDTO = _userProfile

    fun exitChattingRoom(){

    }
    fun sendReport(){

    }

    fun startMatching(){
        viewModelScope.launch {
            when(chatRepository.matching(tokenSharedPreference.getToken("accessToken",""))){
                in (200..300) -> connectStomp()
                !in (200..300) -> /*connectStomp()*/ Log.d(tag,"매칭 실패")

            }
        }
    }
    fun closeProfileDialog(){
        _profileDialog != _profileDialog
    }
    fun closeExitDialog(){
        _exitDialog != _exitDialog
    }
    fun closeReportDialog(){
        _reportDialog != _reportDialog
    }

    fun updateMSG(newValue : String){
        _msg.value = newValue
    }

   @SuppressLint("CheckResult")
   fun sendMSG(){
       viewModelScope.launch {
           val jsonObject = JSONObject().apply {
               put("roomId", "ff576df6-9881-41a4-ac45-2fd48f155ced")
               put("sender", "이경수")
               put("contents", _msg.value)

       }
           stomp.send("/pub/chat/message/ff576df6-9881-41a4-ac45-2fd48f155ced",jsonObject.toString()).subscribe{
               if (it){
                   Log.d(tag,"send Success : $msg")
               }
               else{
                   Log.d(tag,"send Fail : $msg")
               }
           }
           _msg.value = ""
       }

    }
    fun connectStomp(){
        viewModelScope.launch {
            stomp.connect().subscribe(){
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
    }
    fun disconnectStomp(){
        viewModelScope.launch {
            stompConnection.isDisposed
        }
    }
    fun subscribeStomp(){
        viewModelScope.launch {
            stomp.join("/sub/chat/ff576df6-9881-41a4-ac45-2fd48f155ced").subscribe{ message ->
                Log.d("receive", message)
                val data = Gson().fromJson(message,MessageDTO::class.java)
                _chatList.add(data)
            }
        }
    }
    fun unsubscribeStomp(){
        viewModelScope.launch {
            //stomp.join(wss).subscribe{ Log.d(tag,"Unsubscribe Success") }.isDisposed
        }
    }
}