package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.usecase.UserModifyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserModifyViewModel@Inject constructor(private val userModifyUseCase: UserModifyUseCase) : ViewModel() {
    private val _rememberPW  = mutableStateOf("")
    private val _rememberPWCheck  = mutableStateOf("")
    private val _rememberPwEqualOrNot  = mutableStateOf(false)
    private val _rememberTrigger = mutableStateOf(false)
    private val _rememberPwLength  = mutableStateOf(false)
    private val _rememberCode  = mutableStateOf("")
    private val _rememberId  = mutableStateOf("")
    private val _rememberEmail   = mutableStateOf("")

    val rememberPW : State<String> = _rememberPW
    val rememberPWCheck : State<String> = _rememberPWCheck
    val rememberPwEqualOrNot : State<Boolean> = _rememberPwEqualOrNot
    val rememberTrigger : State<Boolean> = _rememberTrigger
    val rememberPwLength : State<Boolean> = _rememberPwLength
    val rememberCode : State<String> = _rememberCode
    val rememberID : State<String> = _rememberId
    val rememberEmail : State<String> = _rememberEmail

    fun updateRememberPW(newValue : String){
        _rememberPW.value = newValue
        updateRememberPwEqualOrNot()
        updateRememberTrigger()
    }
    fun updateRememberPWCheck(newValue : String){
        _rememberPWCheck.value = newValue
        updateRememberPwEqualOrNot()
        updateRememberTrigger()
    }
    private fun updateRememberPwEqualOrNot(){
        _rememberPwEqualOrNot.value = _rememberPW.value == _rememberPWCheck.value
    }

    private fun updateRememberTrigger(){
        checkPwLength()
        _rememberTrigger.value =  _rememberPW.value == _rememberPWCheck.value &&
                _rememberPwLength.value && _rememberPwEqualOrNot.value
    }

    private fun checkPwLength(){
        _rememberPwLength.value = !(_rememberPW.value.length < 6 || _rememberPW.value.length> 20)
    }

    fun postPwChange(){
        viewModelScope.launch {
            userModifyUseCase.pwChange(UserDTO(rememberPW.value,rememberPWCheck.value) )
        }
    }

    fun postAuthCode(){
        viewModelScope.launch {
            userModifyUseCase.postAuthCode(UserDTO(memberID = rememberID.value, memberEmail = rememberEmail.value))
        }
    }

    fun checkAuthCode(){
        viewModelScope.launch {
            userModifyUseCase.checkAuthCode(UserDTO(code = rememberCode.value))
        }
    }

}