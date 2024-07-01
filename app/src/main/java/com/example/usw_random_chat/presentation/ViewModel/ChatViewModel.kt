package com.example.usw_random_chat.presentation.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.TokenInterceptor
import com.example.usw_random_chat.data.dto.MessageDTO
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.domain.repository.ChatRepository
import com.example.usw_random_chat.domain.repository.ProfileRepository
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
    private val profileRepository: ProfileRepository,
    private val tokenInterceptor: TokenInterceptor,
    private val client: OkHttpClient
) : ViewModel() {

    private lateinit var stompConnection: Disposable
    private  var roodID: String = "1234"

    private val tag = "STOMP"
    private val serverUrl: String = "ws://43.202.91.160:8080/stomp"
    private val stomp = StompClient(client, 5000L).apply { this@apply.url = serverUrl }

    private val _chatList = mutableStateListOf<MessageDTO>()
    private val _msg = mutableStateOf("")
    private val _profileDialog = mutableStateOf(false)
    private val _reportDialog = mutableStateOf(false)
    private val _exitDialog = mutableStateOf(false)
    private val _userProfile = mutableStateOf(ProfileDTO("", "", ""))
    private val _opponentUserProfile = mutableStateOf(ProfileDTO("", "", ""))

    val chatList = _chatList
    val msg: State<String> = _msg
    val profileDialog: State<Boolean> = _profileDialog
    val opponentUserProfile : State<ProfileDTO> = _opponentUserProfile
    val exitDialog: State<Boolean> = _exitDialog
    val reportDialog: State<Boolean> = _reportDialog
    val userProfile: State<ProfileDTO> = _userProfile

    fun exitChattingRoom() {
        stomp.join("/queue/match/in/admin").subscribe { Log.d(tag, "Unsubscribe Success") }
            .dispose()

        stomp.join("/queue/match/cancel/admin").subscribe {
           Log.d("receive", it)
        }

        stomp.send("/pub/queue/match/cancel/admin", "").subscribe {
        }

        stomp.join("/queue/match/cancel/admin").subscribe {
            Log.d("receive", it)
        }.dispose()

    }

    fun sendReport() {

    }

    fun startMatching() {
        connectStomp()
        stomp.join("/queue/match/in/admin").subscribe {
            Log.d("receive", it)
            val msg = Gson().fromJson(it, MessageDTO::class.java)
        }
        stomp.send("/pub/queue/match/in/admin", "").subscribe {
            if (it){
                Log.d("asdf","2323")
            }
        }

    }

    fun closeProfileDialog() {
        _profileDialog != _profileDialog
    }

    fun closeExitDialog() {
        _exitDialog != _exitDialog
    }

    fun closeReportDialog() {
        _reportDialog != _reportDialog
    }

    fun updateMSG(newValue: String) {
        _msg.value = newValue
    }

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getProfile()
            if (response.data.mbti == null) {
                _userProfile.value.mbti = "MBTI를 작성해주세요!"
            } else {
                _userProfile.value.mbti = response.data.mbti
            }
            _userProfile.value.nickName = response.data.nickName

            if (response.data.selfIntroduce == null) {
                _userProfile.value.selfIntroduce = "자기소개를 작성해주세요!"
            } else {
                _userProfile.value.selfIntroduce = response.data.selfIntroduce
            }
            Log.d("프로필 mb", response.data?.mbti.toString())
            Log.d("프로필 nick", response.data.nickName)
            Log.d("프로필 self", response.data?.selfIntroduce.toString())

        }
    }

    fun getYourProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = profileRepository.getYourProfile()
            _opponentUserProfile.value.mbti = response.data.mbti

            _opponentUserProfile.value.nickName = response.data.nickName

            _opponentUserProfile.value.selfIntroduce = response.data.selfIntroduce

            Log.d("프로필 mb", response.data?.mbti.toString())
            Log.d("프로필 nick", response.data.nickName)
            Log.d("프로필 self", response.data?.selfIntroduce.toString())

        }
    }

    @SuppressLint("CheckResult")
    fun sendMSG() {
        viewModelScope.launch {
            val jsonObject = JSONObject().apply {
                put("roomId", roodID)
                put("sender", "이경수")
                put("contents", _msg.value)

            }
            stomp.send(
                "/pub/chat/message/$roodID",
                jsonObject.toString()
            ).subscribe {
                if (it) {
                    Log.d(tag, "send Success : ${_msg.value}")
                } else {
                    Log.d(tag, "send Fail : ${_msg.value}")
                }
            }
            _msg.value = ""
        }

    }

    fun connectStomp() {
        viewModelScope.launch {
            stompConnection = stomp.connect().subscribe() {
                when (it.type) {
                    Event.Type.OPENED -> {
                        Log.d(tag, "stomp connect success")
                    }

                    Event.Type.CLOSED -> {
                        Log.d(tag, "stomp close")
                    }

                    Event.Type.ERROR -> {
                        Log.d(tag, "stomp connect fail")
                    }

                    else -> {}
                }
            }
        }
    }

    fun disconnectStomp() {
        viewModelScope.launch {
            stompConnection.dispose()
        }
    }

    fun subscribeStomp() {
        viewModelScope.launch {
            stomp.join("/sub/chat/$roodID").subscribe { message ->
                Log.d("receive", message)
                val data = Gson().fromJson(message, MessageDTO::class.java)
                _chatList.add(data)
            }
        }
    }

    fun unsubscribeStomp() {
        viewModelScope.launch {
           // stomp.join(wss).subscribe{ Log.d(tag,"Unsubscribe Success") }.dispose()
        }
    }
}
