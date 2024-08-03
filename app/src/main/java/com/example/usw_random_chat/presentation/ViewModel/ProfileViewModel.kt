package com.example.usw_random_chat.presentation.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usw_random_chat.data.dto.ProfileDTO
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.repository.ProfileRepository
import com.example.usw_random_chat.domain.usecase.ProfileUseCase
import com.example.usw_random_chat.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _nickname = mutableStateOf("")
    private val _mbti = mutableStateOf("")
    private val _selfintroduce = mutableStateOf("")
    private val _checkMBTI = mutableStateOf(1)
    private val _checkNickname = mutableStateOf(0)
    private val _checkSelfIntroduce = mutableStateOf(0)
    private val _dialogCheckSignUpNickNameState = mutableStateOf(0)

    /*
    0 -> 아무것도 아님
    1 -> 닉변 가능
    2 -> 닉네임 중복
    3 -> 닉네임 30일 제한
    4 -> 중복확인 안하고 프로필 수정한 경우
    */
    private val _checkSignupNickNameState = mutableStateOf(false)

    val nickname: State<String> = _nickname
    val mbti: State<String> = _mbti
    val selfintroduce: State<String> = _selfintroduce
    val checkMBTI: State<Int> = _checkMBTI
    val checkSelfIntroduce: State<Int> = _checkSelfIntroduce
    val dialogCheckSignUpNickNameState: State<Int> = _dialogCheckSignUpNickNameState

    init {
        getProfile()
    }

    fun updateNickname(newValue: String) {
        _nickname.value = newValue
        filterNickName()
    }

    fun updateMBTI(newValue: String) {
        _mbti.value = newValue
        filterMBTI()
    }

    fun updateSelfIntroduce(newValue: String) {
        _selfintroduce.value = newValue
        filterSelfIntroduce()
    }

    fun postProfile() {
        viewModelScope.launch {
            if (_checkSignupNickNameState.value) { //중복확인 한 경우
                val code = profileRepository.setProfile(
                    ProfileDTO(
                        _nickname.value,
                        _mbti.value,
                        _selfintroduce.value
                    )
                )
                Log.d("닉변","1")
                if (code in 400..500) {
                    _dialogCheckSignUpNickNameState.value = 3
                }
            } else if (_nickname.value.length > 1 && !_checkSignupNickNameState.value) {
                profileRepository.setProfile(
                    ProfileDTO(
                        "",
                        _mbti.value,
                        _selfintroduce.value
                    )
                )
                Log.d("닉변","2")
                _dialogCheckSignUpNickNameState.value == 4
            } else {
                val code = profileRepository.setProfile(
                    ProfileDTO(
                        _nickname.value,
                        _mbti.value,
                        _selfintroduce.value
                    )
                )
                Log.d("닉변","3")
                if (code in 200..300){
                    _dialogCheckSignUpNickNameState.value == 5
                }
            }

        }
    }

    private fun getProfile() {
        viewModelScope.launch(Dispatchers.Main) {
            val response = profileRepository.getProfile()
            response.apply {
                _mbti.value = response.data.mbti ?: ""
                _nickname.value = response.data.nickName
                _selfintroduce.value = response.data.selfIntroduce ?: ""
            }
        }
    }

    fun doubleCheckNickname() {
        viewModelScope.launch {
            when (signUpUseCase.checkSignUpNickName(UserDTO(nickname = _nickname.value))) {
                in (200..300) -> {
                    _checkSignupNickNameState.value = true
                    _dialogCheckSignUpNickNameState.value = 1
                    Log.d("qrfqfq", "닉네임중복확인 완")
                }

                !in (200..300) -> _dialogCheckSignUpNickNameState.value = 2
            }
        }
    }

    private fun filterMBTI() {
        if (_mbti.value.length == 4 && _mbti.value[0].code in listOf(69, 73, 101, 105) &&
            _mbti.value[1].code in listOf(78, 83, 110, 115) &&
            _mbti.value[2].code in listOf(70, 84, 102, 116) &&
            _mbti.value[3].code in listOf(74, 80, 106, 112)
        ) {
            _checkMBTI.value = 1
        }else if (_mbti.value.isEmpty()){
            _checkMBTI.value = 0
        }
        else{
            _checkMBTI.value = 2
        }

    }

    private fun filterNickName() {
        if (_nickname.value.length <= 8) {
            _checkNickname.value = 1
        }else if (_nickname.value.isEmpty()){
            _checkNickname.value = 0
        }
        else{
            _checkNickname.value = 2
        }
    }

    private fun filterSelfIntroduce() {
        if (_selfintroduce.value.length <= 40) {
            _checkSelfIntroduce.value = 1
        }else if (_selfintroduce.value.isEmpty()){
            _checkSelfIntroduce.value = 0
        }
        else{
            _checkSelfIntroduce.value = 2
        }

    }

    fun changeCheckSignUpNickNameState() {
        _checkSignupNickNameState.value = !_checkSignupNickNameState.value
    }

    fun changeDialogCheckSignUpNickNameState() {
        _dialogCheckSignUpNickNameState.value = 0
    }
}