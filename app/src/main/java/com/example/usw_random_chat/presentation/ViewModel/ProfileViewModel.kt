package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.domain.repository.ProfileRepository
import com.example.usw_random_chat.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {
    private val _nickname = mutableStateOf("")
    private val _mbti = mutableStateOf("")
    private val _selfintroduce = mutableStateOf("")
    private val _checkMBTI = mutableStateOf(true)
    private val _checkNickname = mutableStateOf(true)
    private val _checkSelfIntroduce = mutableStateOf(true)
    private val _booleanList =
        mutableListOf<Boolean>(_checkMBTI.value, _checkNickname.value, _checkSelfIntroduce.value)

    val nickname: State<String> = _nickname
    val mbti: State<String> = _mbti
    val selfintroduce: State<String> = _selfintroduce
    val checkMBTI: State<Boolean> = _checkMBTI
    val checkNickname: State<Boolean> = _checkNickname
    val checkSelfIntroduce: State<Boolean> = _checkSelfIntroduce
    val booleanList = _booleanList.all { it }
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
            profileRepository.setProfile(ProfileDTO(nickname.value, mbti.value, selfintroduce.value))
        }
    }

    fun checkProfile() {
        viewModelScope.launch {
            launch { filterMBTI() }
            launch { filterNickName() }
            launch { filterSelfIntroduce() }
        }
    }

    private fun filterMBTI() {
        if (_mbti.value.length != 4) {
            _checkMBTI.value = false
        } else {
            val validMBTI = _mbti.value[0].code in listOf(69, 73, 101, 105) &&
                    _mbti.value[1].code in listOf(78, 83, 110, 115) &&
                    _mbti.value[2].code in listOf(70, 84, 102, 116) &&
                    _mbti.value[3].code in listOf(74, 80, 106, 112)

            _checkMBTI.value = validMBTI
        }
    }

    private fun filterNickName() {
        if (_nickname.value.length > 8) {
            _checkNickname.value = false
        }
    }

    private fun filterSelfIntroduce() {
        if (_selfintroduce.value.length > 40) {
            _checkSelfIntroduce.value = false
        }
    }
}