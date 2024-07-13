package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val tokenSharedPreference : TokenSharedPreference,
    ) : ViewModel() {

    private val _id = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _loginState = mutableStateOf(false)
    private val _dialogState = mutableStateOf(false)


    val loginState : State<Boolean> = _loginState
    val id : State<String> = _id
    val password : State<String>  = _password
    val dialogState : State<Boolean> = _dialogState


    fun updateID(newValue : String){
        _id.value = newValue
    }

    fun updatePassWord(newValue : String){
        _password.value = newValue
    }
    fun changeLoginState(){
        _loginState.value = !_loginState.value
    }
    fun changeDrawerState(){
        _dialogState.value = !_dialogState.value
    }
    fun postSignIn(){
        viewModelScope.launch {//viewModelScope 공부하기
            when(signInUseCase.execute(UserDTO(memberID = id.value, memberPassword = password.value))){
                in (200..300) -> {
                    _loginState.value = id.value.isNotEmpty() && password.value.isNotEmpty()
                    tokenSharedPreference.setID("ID",_id.value)
                }
                !in (200..300) -> _dialogState.value = true
            }
        }
    }

    fun autoSignIn(){
        viewModelScope.launch{
            val refreshToken = tokenSharedPreference.getToken("refreshToken","")

            when(signInUseCase.autoSignIn(refreshToken)){
                in 200..300 -> _loginState.value = true
            }
        }
    }


}