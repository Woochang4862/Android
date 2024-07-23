package com.example.usw_random_chat.presentation.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usw_random_chat.data.dto.PassWordDTO
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.domain.repository.UserModifyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserModifyViewModel @Inject constructor(
    //private val userModifyUseCase: UserModifyUseCase
    private val userModifyRepository: UserModifyRepository
) : ViewModel() {

    private val _rememberPW = mutableStateOf("")
    private val _rememberPWCheck = mutableStateOf("")
    private val _email = mutableStateOf("")
    private val _rememberPwEqualOrNot = mutableStateOf(false)
    private val _rememberTrigger = mutableStateOf(false)
    private val _rememberPwLength = mutableStateOf(false)
    private val _rememberCode = mutableStateOf("")
    private val _rememberId = mutableStateOf("")
    private val _checkIdSearchAuthEmail = mutableStateOf(false)
    private val _dialogCheckIdSearchAuthEmail = mutableStateOf(false)

    private val _checkPWCode = mutableStateOf(0)
    private val _changePWValue = mutableStateOf(0)
    //private val _dialogCheckIdSearchAuthEmail = mutableStateOf(false)

    val rememberPW: State<String> = _rememberPW
    val rememberPWCheck: State<String> = _rememberPWCheck
    val rememberPwEqualOrNot: State<Boolean> = _rememberPwEqualOrNot
    val rememberTrigger: State<Boolean> = _rememberTrigger
    val rememberPwLength: State<Boolean> = _rememberPwLength
    val email: State<String> = _email
    val checkIdSearchAuthEmail: State<Boolean> = _checkIdSearchAuthEmail
    val dialogCheckIdSearchAuthEmail: State<Boolean> = _dialogCheckIdSearchAuthEmail
    val rememberCode: State<String> = _rememberCode
    val rememberID: State<String> = _rememberId
    val checkPWCode = _checkPWCode
    val changePWValue = _changePWValue


    fun updateCode(newValue: String) {
        _rememberCode.value = newValue
    }

    fun updateId(newValue: String) {
        _rememberId.value = newValue
    }

    fun updateEmail(newValue: String) {
        _email.value = newValue
    }

    fun changeCheckIdSearchAuthEmail() {
        _checkIdSearchAuthEmail.value = !_checkIdSearchAuthEmail.value
    }

    fun changeDialogCheckIdSearchAuthEmail() {
        _dialogCheckIdSearchAuthEmail.value = !_dialogCheckIdSearchAuthEmail.value
    }

    fun changePWCodeDialog(){
        _checkPWCode.value = 0
    }

    fun changePWValueDialog(){
        _changePWValue.value = 0
    }

    fun updateRememberPW(newValue: String) {
        _rememberPW.value = newValue
        updateRememberPwEqualOrNot()
        updateRememberTrigger()
    }

    fun updateRememberPWCheck(newValue: String) {
        _rememberPWCheck.value = newValue
        updateRememberPwEqualOrNot()
        updateRememberTrigger()
    }

    private fun updateRememberPwEqualOrNot() {
        _rememberPwEqualOrNot.value = _rememberPW.value == _rememberPWCheck.value
    }

    private fun updateRememberTrigger() {
        checkPwLength()
        _rememberTrigger.value = _rememberPW.value == _rememberPWCheck.value &&
                _rememberPwLength.value && _rememberPwEqualOrNot.value
    }

    private fun checkPwLength() {
        _rememberPwLength.value = !(_rememberPW.value.length < 6 || _rememberPW.value.length > 20)
    }


    fun createPWChangeCode() {
        viewModelScope.launch {
            when(userModifyRepository.createPWChangeCode(UserDTO(memberID = _rememberId.value, email = _email.value))) {
                in (200..300) -> _checkPWCode.value = 1
                !in (200..300) -> _checkPWCode.value = 2
            }
            Log.d("PWPW","!@#!@#")
        }
    }

    fun checkAuthCode(){
        viewModelScope.launch{

        }
    }

    fun changePW() {
        viewModelScope.launch {
            when(userModifyRepository.changePW(PassWordDTO(_rememberPW.value,_rememberPWCheck.value))){
                in (200..300) -> _changePWValue.value = 1
                !in (200..300) -> _changePWValue.value = 2
            }
        }
    }

    fun findUserID() {   //아이디 찾기에 있는 확인 메일 전송 버튼 함수 //완료
        viewModelScope.launch {
            when (userModifyRepository.findUserID(_email.value)) {
                in (200..300) -> _checkIdSearchAuthEmail.value = true
                !in (200..300) -> _dialogCheckIdSearchAuthEmail.value = true
            }
        }
    }


}