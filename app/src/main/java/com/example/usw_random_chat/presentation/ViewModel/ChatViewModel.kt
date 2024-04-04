package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.MessageDTO
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val tokenSharedPreference: TokenSharedPreference
) : ViewModel() {
    private val _msg = mutableStateOf("")
    private val _profileDialog = mutableStateOf(false)
    private val _reportDialog = mutableStateOf(false)
    private val _exitDialog = mutableStateOf(false)
    private val _userProfile : ProfileDTO = ProfileDTO()

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

   fun sendMSG(){
       viewModelScope.launch {
           chatRepository.sendMsg(Json.encodeToString(MessageDTO("1234","이경수",_msg.value)),"/pub/chat/message/1234")
       }
    }
    fun connectStomp(){
        viewModelScope.launch {
            chatRepository.connectStomp()
        }
    }
    fun disconnectStomp(){
        viewModelScope.launch {
            chatRepository.disconnectStomp()
        }
    }
    fun subscribeStomp(){
        viewModelScope.launch {
            chatRepository.subscribeStomp("/sub/chat/room/1234" )
        }
    }
    fun unsubscribeStomp(){
        viewModelScope.launch {
            chatRepository.unsubscribeStomp("/sub/chat/room/1234")
        }
    }
}