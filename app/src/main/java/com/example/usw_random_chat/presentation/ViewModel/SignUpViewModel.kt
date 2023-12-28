package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel()  {
    private val _rememberId  = mutableStateOf("")
    private val _rememberPw  = mutableStateOf("")
    private val _rememberPwCheck  = mutableStateOf("")
    //private val _rememberEmail   = mutableStateOf("")
    private val _rememberPwEqualOrNot = mutableStateOf(false)
    private val _rememberTrigger = mutableStateOf(false)
    private val _rememberIdLength  = mutableStateOf(false)

    val rememberId : State<String> = _rememberId
    val rememberPw : State<String>  = _rememberPw
    val rememberPwCheck : State<String>  = _rememberPwCheck
    //val rememberEmail : State<String>  = _rememberEmail
    val rememberPwEqualOrNot : State<Boolean> = _rememberPwEqualOrNot
    val rememberTrigger : State<Boolean> = _rememberTrigger
    val rememberIdLength : State<Boolean> = _rememberIdLength


    fun updateRememberId(newValue : String){
        _rememberId.value = newValue
        updateRememberTrigger()
    }
    fun updateRememberPw(newValue : String){
        _rememberPw.value = newValue
        updateRememberTrigger()
    }
    fun updateRememberPwCheck(newValue : String){
        _rememberPwCheck.value = newValue
        updateRememberPwEqualOrNot()
        updateRememberTrigger()
    }

    private fun updateRememberPwEqualOrNot(){
        _rememberPwEqualOrNot.value = _rememberPw.value == _rememberPwCheck.value
        updateRememberTrigger()
    }
    private fun updateRememberTrigger(){
        checkIdLength()
        _rememberTrigger.value =  _rememberPw.value == _rememberPwCheck.value &&
                _rememberIdLength.value && _rememberPwEqualOrNot.value
    }

    private fun checkIdLength(){
        _rememberIdLength.value = !(_rememberId.value.length < 4 || _rememberId.value.length> 16)
    }

    fun postSignUp(){
        viewModelScope.launch {
            signUpUseCase.signUp(UserDTO(rememberId.value,rememberPw.value) )
        }
    }
    /*fun updateRememberEmail(newValue : String){
        _rememberEmail.value = newValue
        updateRememberTrigger()
    }*/

}