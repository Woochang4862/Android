package com.example.usw_random_chat.presentation.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.usecase.SignInUseCase
import com.example.usw_random_chat.presentation.view.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase, private val navController: NavController) : ViewModel() {

    private val _id = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _signInValue = mutableStateOf(false)
    private val _loginState = mutableStateOf(false)

    val loginState : State<Boolean> = _loginState
    val id : State<String> = _id
    val password : State<String>  = _password
    val signInValue : State<Boolean> = _signInValue

    fun updateID(newValue : String){
        _id.value = newValue
    }

    fun updatePassWord(newValue : String){
        _password.value = newValue
    }

    fun changeLoginState(){
        _loginState.value = !_loginState.value
    }

    fun postSignIn(){
        viewModelScope.launch {//viewModelScope 공부하기
            when(signInUseCase.execute(UserDTO(memberID = id.value,memberPassword = password.value))){
                !in (200..300) -> _loginState.value = true
                in 200..300 -> navController.navigate(Screen.MainPageScreen.route){
                    navController.popBackStack()
                }
            }
        }
    }
}