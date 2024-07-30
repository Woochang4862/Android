package com.example.usw_random_chat.presentation.ViewModel

import android.util.Log
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
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _nickName = mutableStateOf("")
    private val _rememberId = mutableStateOf("")
    private val _rememberPw = mutableStateOf("")
    private val _rememberPwCheck = mutableStateOf("")
    private val _email = mutableStateOf("")

    private val _authEmailState = mutableStateOf(false)
    private val _checkAuthEmailState = mutableStateOf(false)
    private val _dialogAuthEmailState = mutableStateOf<Int>(2)

    private val _rememberPwEqualOrNot = mutableStateOf(false)
    private val _rememberTrigger = mutableStateOf(false)
    private val _rememberIdLength = mutableStateOf(false)

    private val _checkSignupIdState = mutableStateOf(false)
    private val _dialogCheckSignUpIdState = mutableStateOf(false)
    private val _checkSignupNickNameState = mutableStateOf(false)
    private val _dialogCheckSignUpNickNameState = mutableStateOf(false)
    private val _signUpIdState = mutableStateOf(false)
    private val _signUpNickNameState = mutableStateOf(false)

    val rememberId: State<String> = _rememberId
    val nickName: State<String> = _nickName
    val rememberPw: State<String> = _rememberPw
    val rememberPwCheck: State<String> = _rememberPwCheck
    val email: State<String> = _email
    val rememberPwEqualOrNot : State<Boolean> = _rememberPwEqualOrNot
    val rememberTrigger : State<Boolean> = _rememberTrigger
    val rememberIdLength : State<Boolean> = _rememberIdLength
    val authEmailState : State<Boolean> = _authEmailState
    val checkAuthEmailState : State<Boolean> = _checkAuthEmailState
    val dialogAuthEmailState : State<Int> = _dialogAuthEmailState
    val checkSignupIdState : State<Boolean> = _checkSignupIdState
    val dialogCheckSignUpIdState : State<Boolean> = _dialogCheckSignUpIdState
    val checkSignupNickNameState : State<Boolean> = _checkSignupNickNameState
    val dialogCheckSignUpNickNameState : State<Boolean> = _dialogCheckSignUpNickNameState

    fun postEmail() {
        viewModelScope.launch {
            _dialogAuthEmailState.value = 1
            when(signUpUseCase.signUp(UserDTO(memberID = _rememberId.value, memberPassword = rememberPw.value, nickname = nickName.value, email = email.value))){
                //in (200..300) -> _dialogAuthEmailState.value = 1 // 성공
               //!in (200..300) -> _dialogAuthEmailState.value = 0 //실패
            }
        }
    }

    fun checkSignUpId() {   //중복확인버튼에 사용할 함수
        viewModelScope.launch {
            when(signUpUseCase.idDoubleCheck(UserDTO(memberID = _rememberId.value))){
                in (200..300) -> _checkSignupIdState.value = true
                !in (200..300) -> _dialogCheckSignUpIdState.value = true
            }
            updateRememberTrigger()
        }
    }

    fun checkSignUpNickName() {   //회원가입 닉네임 중복 확인에 쓸 함수
        viewModelScope.launch {
            when(signUpUseCase.checkSignUpNickName(UserDTO(nickname = nickName.value))){
                in (200..300) -> _checkSignupNickNameState.value = true
                !in (200..300) -> _dialogCheckSignUpNickNameState.value = true
            }
            updateRememberTrigger()
        }
    }

    fun checkEmailAuth() {   // 이메일 인증 확인
        viewModelScope.launch {
            when(signUpUseCase.checkAuthEmail()){
                in (200..300) -> _checkAuthEmailState.value = true
                !in (200..300) -> Log.d("이메일 인증 실패","이메일 인증이 안돼요")
            }

        }
    }

    fun updateEmail(newValue: String){
        _email.value = newValue
    }

    fun updateRememberId(newValue: String) {
        _rememberId.value = newValue
        updateRememberTrigger()
    }

    fun updateRememberNickName(newValue: String) {
        _nickName.value = newValue
        updateRememberTrigger()
    }//이것 닉네임에 쓸 함수

    fun updateRememberPw(newValue: String) {
        _rememberPw.value = newValue
        updateRememberTrigger()
    }

    fun updateRememberPwCheck(newValue: String) {
        _rememberPwCheck.value = newValue
        updateRememberPwEqualOrNot()
        updateRememberTrigger()
    }

    private fun updateRememberPwEqualOrNot() {
        _rememberPwEqualOrNot.value = _rememberPw.value == _rememberPwCheck.value
        updateRememberTrigger()
    }
    fun changeCheckSignUpNickNameState(){
        _checkSignupNickNameState.value = !_checkSignupNickNameState.value
        _signUpNickNameState.value = true
    }
    fun changeDialogCheckSignUpNickNameState(){
        _dialogCheckSignUpNickNameState.value = !_dialogCheckSignUpNickNameState.value
    }
    fun changeCheckSignUpIdState(){
        _checkSignupIdState.value = !_checkSignupIdState.value// 새로 만든 boolean값 여기에 같이 바꿨어요
        _signUpIdState.value = true//그러면 성공했을때 RememberTrigger 값이 true가 되면 버튼 활성화 됩니다
    }
    fun changeDialogCheckSignUpIdState(){
        _dialogCheckSignUpIdState.value = !_dialogCheckSignUpIdState.value
    }
    fun changeAuthEmailState(){
        _authEmailState.value = !_authEmailState.value
    }

    fun changeDialogAuthEmailState(){
        _dialogAuthEmailState.value = 2
    }


    private fun updateRememberTrigger() {
        checkIdLength()
        _rememberTrigger.value = _signUpIdState.value && _rememberPwEqualOrNot.value && _signUpNickNameState.value
    }

    private fun checkIdLength() {
        _rememberIdLength.value = !(_rememberId.value.length < 4 || _rememberId.value.length > 16)
    }
}