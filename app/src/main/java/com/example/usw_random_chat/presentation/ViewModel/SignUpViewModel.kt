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
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _nickName = mutableStateOf("")
    private val _rememberId = mutableStateOf("")
    private val _rememberPw = mutableStateOf("")
    private val _rememberPwCheck = mutableStateOf("")
    private val _email = mutableStateOf("")
    private val _signupState = mutableStateOf(false)
    private val _authEmailState = mutableStateOf(false)
    private val _checkAuthEmailState = mutableStateOf(false)
    private val _dialogSignupState = mutableStateOf(false)
    private val _dialogAuthEmailState = mutableStateOf(false)
    private val _dialogCheckAuthEmailState = mutableStateOf(false)
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
    val signupState : State<Boolean> = _signupState
    val authEmailState : State<Boolean> = _authEmailState
    val checkAuthEmailState : State<Boolean> = _checkAuthEmailState
    val dialogSignupState : State<Boolean> = _dialogSignupState
    val dialogAuthEmailState : State<Boolean> = _dialogAuthEmailState
    val dialogCheckAuthEmailState : State<Boolean> = _dialogCheckAuthEmailState
    val checkSignupIdState : State<Boolean> = _checkSignupIdState
    val dialogCheckSignUpIdState : State<Boolean> = _dialogCheckSignUpIdState
    val checkSignupNickNameState : State<Boolean> = _checkSignupNickNameState
    val dialogCheckSignUpNickNameState : State<Boolean> = _dialogCheckSignUpNickNameState

    fun verifyEmail() {
        viewModelScope.launch {
            when(signUpUseCase.reAuthEmail(UserDTO(memberID = rememberId.value, memberPassword = rememberPw.value, nickname = nickName.value, email = email.value))){
                in (200..300) -> _authEmailState.value = true
                !in (200..300) -> _dialogAuthEmailState.value = true
            }
        }
    }

    fun checkVerifyEmail() {
        viewModelScope.launch {
            when(signUpUseCase.checkDoubleEmail(UserDTO(email = email.value))){
                in (200..300) -> _checkAuthEmailState.value = true
                !in (200..300) -> _dialogCheckAuthEmailState.value = true
            }
        }
    }

    fun checkSignUpId() {   //중복확인버튼에 사용할 함수
        viewModelScope.launch {
            when(signUpUseCase.idDoubleCheck(UserDTO(memberID = rememberId.value))){
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
    fun changeDialogSignupState(){
        _dialogSignupState.value = !_dialogSignupState.value
    }
    fun changeDialogAuthEmailState(){
        _dialogAuthEmailState.value = !_dialogAuthEmailState.value
    }
    fun changeDialogCheckAuthEmailState(){
        _dialogCheckAuthEmailState.value = !_dialogCheckAuthEmailState.value
    }
    fun changeCheckAuthEmailState(){
        _checkAuthEmailState.value = !_checkAuthEmailState.value
    }
    fun changeSignupState(){
        _signupState.value = !_signupState.value
    }

    private fun updateRememberTrigger() {
        checkIdLength()
        _rememberTrigger.value = _signUpIdState.value && _rememberPwEqualOrNot.value && _signUpNickNameState.value
    }//_rememberPw.value == _rememberPwCheck.value 이건 지웠어요 이게 _rememberPwEqualOrNot랑 같아서
    //그리고 _signUpIdState랑 _signUpNickNameState로 새로 만들었어요 생각해보니까 change함수로 다시 false만들면 버튼활성화가 안되서요 이 boolean값은 if문 안에 넣었습니다

    private fun checkIdLength() {
        _rememberIdLength.value = !(_rememberId.value.length < 4 || _rememberId.value.length > 16)
    }
}