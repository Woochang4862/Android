package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repositoryimpl.ChatRepositoryImpl
import com.example.usw_random_chat.data.repositoryimpl.ChatRepositoryImpl.Server.server_url
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor( private val chatRepositoryImpl: ChatRepositoryImpl) : ViewModel() {
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

   fun sendMSG(msg : String){
       viewModelScope.launch {
           chatRepositoryImpl.sendMsg(msg,server_url)
       }
    }
    fun connectStomp(){
        viewModelScope.launch {
            chatRepositoryImpl.connectStomp()
        }
    }
    fun disconnectStomp(){
        viewModelScope.launch {
            chatRepositoryImpl.disconnectStomp()
        }
    }
    fun subscribeStomp(){
        viewModelScope.launch {
            chatRepositoryImpl.subscribeStomp(server_url)
        }
    }
    fun unsubscribeStomp(){
        viewModelScope.launch {
            chatRepositoryImpl.unsubscribeStomp(server_url)
        }
    }
}