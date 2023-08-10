package com.example.usw_random_chat.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    var _nickname by mutableStateOf("")
        private set
    private var _mbti = mutableStateOf("")
    private val _selfintroduce = mutableStateOf("")

    //val nickname : String = _nickname.value
    val mbti : State<String>  get() = _mbti
    val selfintroduce : State<String>  get() = _selfintroduce

    fun updateNickname(newValue : String){
        _nickname = newValue
    }
}