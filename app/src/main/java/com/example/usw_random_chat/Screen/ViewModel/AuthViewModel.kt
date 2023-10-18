package com.example.usw_random_chat.Screen.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.usecase.RegisterUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    fun signinViewmodel(param : UserDTO){
        viewModelScope.launch {//viewModelScope 공부하기
            try {
                registerUseCase.excute(param)
            }
            catch (e:Exception){
                Log.e("에러!!",e.localizedMessage)
            }
        }
    }
}