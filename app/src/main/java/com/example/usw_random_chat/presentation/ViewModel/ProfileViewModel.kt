package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) : ViewModel() {
    private val _nickname = mutableStateOf("")
    private val _mbti = mutableStateOf("")
    private val _selfintroduce = mutableStateOf("")

    val nickname : State<String> = _nickname
    val mbti : State<String>  = _mbti
    val selfintroduce : State<String>  = _selfintroduce

    fun updateNickname(newValue : String){
        _nickname.value = newValue
    }
    fun updateMBTI(newValue : String){
        _mbti.value = newValue
    }
    fun updateSelfIntroduce(newValue : String){
        _selfintroduce.value = newValue
    }

    fun postProfile(){
        viewModelScope.launch {
            profileUseCase.excute(ProfileDTO(nickname.value,mbti.value,selfintroduce.value))
        }
    }
}