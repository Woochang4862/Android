package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.usw_random_chat.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope


@HiltViewModel
class SignUpViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel()  {
    private val _rememberId  = mutableStateOf("")
    private val _rememberPw  = mutableStateOf("")
    private val _rememberPwCheck  = mutableStateOf("")
    private val _rememberEmail   = mutableStateOf("")
    private val _rememberPwEqualOrNot = mutableStateOf(false)
    private val _rememberTrigger = mutableStateOf(false)

    val rememberId : State<String> = _rememberId
    val rememberPw : State<String>  = _rememberPw
    val rememberPwCheck : State<String>  = _rememberPwCheck
    val rememberEmail : State<String>  = _rememberEmail
    val rememberPwEqualOrNot : State<Boolean> = _rememberPwEqualOrNot
    val rememberTrigger : State<Boolean> = _rememberTrigger

    fun updateRememberId(newValue : String){
        _rememberId.value = newValue
    }
    fun updateRememberPw(newValue : String){
        _rememberPw.value = newValue
    }
    fun updateRememberPwCheck(newValue : String){
        _rememberPwCheck.value = newValue
        updateRememberPwEqualOrNot()
    }
    fun updateRememberEmail(newValue : String){
        _rememberEmail.value = newValue
        updateRememberTrigger()
    }
    fun updateRememberPwEqualOrNot(){
        _rememberPwEqualOrNot.value = _rememberPw.value == _rememberPwCheck.value

    }
    fun updateRememberTrigger(){
        _rememberTrigger.value =  _rememberPw.value == _rememberPwCheck.value &&
                _rememberId.value.isNotEmpty() &&
                _rememberEmail.value.isNotEmpty() && _rememberPwEqualOrNot.value
    }

    fun onPress(){

    }
}