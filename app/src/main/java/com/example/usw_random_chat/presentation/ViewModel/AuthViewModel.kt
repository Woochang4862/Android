package com.example.usw_random_chat.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {
    fun signinViewmodel(param : UserDTO){
        viewModelScope.launch {//viewModelScope 공부하기
            registerUseCase.excute(param)
        }
    }
    fun signUpViewModel(param : UserDTO){
        viewModelScope.launch {//viewModelScope 공부하기
            registerUseCase.excute(param)
        }
    }
}