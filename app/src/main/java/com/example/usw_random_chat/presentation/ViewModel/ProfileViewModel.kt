package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) :
    ViewModel() {
    private val _nickname = mutableStateOf("")
    private val _mbti = mutableStateOf("")
    private val _selfintroduce = mutableStateOf("")
    private val _checkMBTI = mutableStateOf(true)
    private val _checkNickname = mutableStateOf(true)
    private val _checkSelfIntroduce = mutableStateOf(true)

    val nickname: State<String> = _nickname
    val mbti: State<String> = _mbti
    val selfintroduce: State<String> = _selfintroduce
    val checkMBTI: State<Boolean> = _checkMBTI
    val checkNickname: State<Boolean> = _checkNickname
    val checkSelfIntroduce: State<Boolean> = _checkSelfIntroduce

    fun updateNickname(newValue: String) {
        _nickname.value = newValue
    }

    fun updateMBTI(newValue: String) {
        _mbti.value = newValue
    }

    fun updateSelfIntroduce(newValue: String) {
        _selfintroduce.value = newValue
    }

    fun postProfile() {
        viewModelScope.launch {
            profileUseCase.excute(ProfileDTO(nickname.value, mbti.value, selfintroduce.value))
        }
    }

    fun filterMBTI() : Boolean {
        if (_mbti.value.length != 4) {
            return false
        } else {
            if (_mbti.value[0].code != 69 || _mbti.value[0].code != 73 || _mbti.value[0].code != 101 || _mbti.value[0].code != 105) {
                return false
            } else if (_mbti.value[1].code != 78 || _mbti.value[1].code != 83 || _mbti.value[1].code != 110 || _mbti.value[1].code != 115) {
                return false
            } else if (_mbti.value[2].code != 70 || _mbti.value[2].code != 84 || _mbti.value[2].code != 102 || _mbti.value[2].code != 116) {
                return false
            } else if (_mbti.value[3].code != 74 || _mbti.value[3].code != 80 || _mbti.value[3].code != 106 || _mbti.value[3].code != 112) {
                return false
            }
        }
        return true
    }

    fun filterNickName() : Boolean {
        if (_nickname.value.length > 8){
            return false
        }
        return true
    }

    fun filterSelfIntroduce() : Boolean {
        if (_selfintroduce.value.length > 40){
            return false
        }
        return true
    }
}