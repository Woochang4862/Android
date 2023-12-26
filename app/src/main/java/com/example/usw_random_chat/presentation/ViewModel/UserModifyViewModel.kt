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
class UserModifyViewModel@Inject constructor(private val UserModifyUseCase: UserModifyUseCase) : ViewModel() {
    private val _rememberPW  = mutableStateOf("")
    private val _rememberPWCheck  = mutableStateOf("")
    private val _rememberPwEqualOrNot  = mutableStateOf(false)
    private val _rememberTrigger = mutableStateOf(false)
    private val _rememberPwLength  = mutableStateOf(false)

    val rememberPW : State<String> = _rememberPW
    val rememberPWCheck : State<String> = _rememberPWCheck
    val rememberPwEqualOrNot : State<Boolean> = _rememberPwEqualOrNot
    val rememberTrigger : State<Boolean> = _rememberTrigger
    val rememberPwLength : State<Boolean> = _rememberPwLength

    fun updaterememberPW(newValue : String){
        _rememberPW.value = newValue
        updaterememberPwEqualOrNot()
        updateRememberTrigger()
    }
    fun updaterememberPWCheck(newValue : String){
        _rememberPWCheck.value = newValue
        updaterememberPwEqualOrNot()
        updateRememberTrigger()
    }
    fun updaterememberPwEqualOrNot(){
        _rememberPwEqualOrNot.value = _rememberPW.value == _rememberPWCheck.value
    }

    fun updateRememberTrigger(){
        PwlengthCheck()
        _rememberTrigger.value =  _rememberPW.value == _rememberPWCheck.value &&
                _rememberPwLength.value && _rememberPwEqualOrNot.value
    }

    fun PwlengthCheck(){
        if(_rememberPW.value.length < 6 || _rememberPW.value.length> 20){
            _rememberPwLength.value = false
        }
        else{
            _rememberPwLength.value = true
        }
    }

    fun postPwChange(){
        viewModelScope.launch {
            UserModifyUseCase.pwChange(UserDTO(rememberPW.value,rememberPWCheck.value) )
        }
    }
    /*fun signUpViewModel(param : UserDTO){
        viewModelScope.launch {//viewModelScope 공부하기
            signInUseCase.excute(param)
        }
    }*/
}