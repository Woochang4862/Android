package com.example.usw_random_chat.presentation.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import okhttp3.OkHttpClient
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val profileRepository: ProfileRepository,
    private val tokenInterceptor: TokenInterceptor,
    private val tokenSharedPreference: TokenSharedPreference,
    private val client: OkHttpClient
) : ViewModel() {

    private lateinit var stompConnection: Disposable
    private lateinit var roodID: String
    private lateinit var targetAccount : String
    private val userID = tokenSharedPreference.getID("ID","")
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
    private val _matchingPresence = mutableStateOf(false)

    private val _checkDeleteMemberDialog = mutableStateOf(false)
    private val _deleteMemberDialog = mutableStateOf(false)

    val matchingPresence = _matchingPresence
    val chatList = _chatList
    val msg: State<String> = _msg
    val profileDialog: State<Boolean> = _profileDialog
    val opponentUserProfile : State<ProfileDTO> = _opponentUserProfile
    val exitDialog: State<Boolean> = _exitDialog
    val reportDialog: State<Boolean> = _reportDialog
    val userProfile: State<ProfileDTO> = _userProfile
    val deleteMemberDialog = _deleteMemberDialog
    val checkDeleteMemberDialog = _checkDeleteMemberDialog

    val textList = mutableStateListOf(
        "체대옆 공터는 원래 방송반이 있던 큰 건물이었습니다.",
        "떴다분수(본관앞)의 유래는 총장님이 오셨을때만 분수가 켜져서 떴다분수 입니다.",
        "수원대<->수원역 가장 빠른 버스는 700-2, 6-1입니다.",
        "음대로 가는 오르막 길의 경사도는 66도입니다.",
        "데이터 과학부는 IT건물이 아닌 글로벌 경상관에 있습니다.",
        "벚꽃 시즌의 수원대는 정말 아름답습니다.",
    )

    fun exitChattingRoom() {
        stomp.join("/sub/chat/$roodID").subscribe{}.dispose()
        disconnectStomp()
        _chatList.clear()
    }

    fun stopMatching(){
        stomp.join("/queue/match/in/$userID").subscribe { Log.d(tag, "Unsubscribe Success") }.dispose()

        stomp.join("/queue/match/cancel/$userID").subscribe {
            Log.d("receive", it)
        }

        stomp.send("/pub/queue/match/cancel/$userID", "").subscribe {
        }

        stomp.join("/queue/match/cancel/$userID").subscribe {
            Log.d("receive", it)
        }.dispose()

        disconnectStomp()
    }

    private fun changeMatchingPresence(){
        _matchingPresence.value = !_matchingPresence.value
    }

    @SuppressLint("CheckResult")
    fun startMatching() {
        connectStomp()
        stomp.join("/queue/match/in/$userID").subscribe {
            Log.d("receive", it)
            if(it.slice(1..4) == "매칭완료"){
                roodID = it.slice(6..41)
                targetAccount = it.substring(43)
                Log.d("RoomID",roodID)
                Log.d("targetAccount",targetAccount)
                stomp.join("/queue/match/in/$userID").subscribe { Log.d(tag, "Unsubscribe Success") }.dispose()
                subscribeStomp()
                changeMatchingPresence()
            }
        }
        stomp.send("/pub/queue/match/in/$userID", "").subscribe {
            if (it){
                Log.d("startMatching","2323")
            }
        }
    }

    fun closeProfileDialog() {
        _profileDialog.value = !_profileDialog.value
    }

    fun closeExitDialog() {
        _exitDialog.value = !_exitDialog.value
    }

    fun closeReportDialog() {
        _reportDialog.value = !_reportDialog.value
    }

    fun updateMSG(newValue: String) {
        _msg.value = newValue
    }


    fun changeDeleteMemberDialog(){
        _deleteMemberDialog.value = !_deleteMemberDialog.value
    }

    fun changeCheckDeleteMemberDialog(){
        _checkDeleteMemberDialog.value = !_checkDeleteMemberDialog.value
    }

    fun deleteMember(){
        viewModelScope.launch {
            when(profileRepository.deleteMember()){
                in(200..300) -> {
                    _deleteMemberDialog.value = true
                    tokenSharedPreference.setToken("accessToken","")
                    tokenSharedPreference.setToken("refreshToken","")
                }
            }

        }
    }

    fun logout(){
        viewModelScope.launch {
            when(profileRepository.logout(tokenSharedPreference.getToken("refreshToken",""))){
                in (200..300) -> {
                    tokenSharedPreference.setToken("accessToken","")
                    tokenSharedPreference.setToken("refreshToken","")
                }
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = profileRepository.getProfile()
            _userProfile.value.apply {
                mbti = response.data.mbti ?: "MBTI를 작성해주세요!"
                nickName = response.data.nickName
                selfIntroduce = response.data.selfIntroduce ?:"자기소개를 작성해주세요!"
            }
        }
    }

    fun getYourProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            changeMatchingPresence()
            val response = profileRepository.getYourProfile(targetAccount)

            _opponentUserProfile.value?.apply {
                mbti = response.data.mbti ?: "값을 넣어주세요"
                nickName = response.data.nickName ?: "값을 넣어주세요"
                selfIntroduce = response.data.selfIntroduce ?: "값을 넣어주세요"
            }
        }
    }

    @SuppressLint("CheckResult")
    fun sendMSG() {
        viewModelScope.launch {
            val jsonObject = JSONObject().apply {
                put("roomId", roodID)
                put("sender", _userProfile.value.nickName)
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

    private fun connectStomp() {
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

    private fun disconnectStomp() {
        viewModelScope.launch {
            stompConnection.dispose()
        }
    }

    private fun subscribeStomp() {
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
