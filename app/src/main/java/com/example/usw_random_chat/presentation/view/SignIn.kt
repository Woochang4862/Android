package com.example.usw_random_chat.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignInViewModel


@Composable // 제가 만들어 놓은 viewmodel 함수를 적용해서 완벽한 signin 화면을 만들어주세요, 어려우면 profile 화면 참고!!
fun SignInScreen(signInViewModel: SignInViewModel = viewModel(), navController: NavController) {

    val focusRequesterID = remember { FocusRequester() }
    val focusRequesterPW = remember { FocusRequester() }
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        LoginImage()
        Spacer(modifier = Modifier.height(90.dp))
        Column (
            modifier = Modifier
                .padding(horizontal = 32.dp)
        ) {
            LoginTextFieldID(
                text = signInViewModel.id,
                text2 = "ID",
                focusRequesterID,
                focusRequesterPW
            ) { newId -> signInViewModel.updateID(newId) }
            Spacer(modifier = Modifier.height(10.dp))
            LoginTextFieldPW(
                text = signInViewModel.password,
                text2 = "PASSWORD",
                focusRequesterPW
            ) { signInViewModel.updatePassWord(it) }
            Spacer(modifier = Modifier.height(10.dp))
            LoginBtn() { signInViewModel.postSignIn() }
            OnLoginFindIdAndPassword(navController)
            Spacer(modifier = Modifier.height(10.dp))
            MadeAccountText()
            Spacer(modifier = Modifier.height(10.dp))
            SignUpBtn(navController)
        }

        if (signInViewModel.loginState.value) {
            navController.navigate(Screen.MainPageScreen.route) {
                popUpTo(navController.graph.id) { inclusive = true }
            }
            signInViewModel.changeLoginState()
        }
        if (signInViewModel.dialogState.value) {
            OneButtonDialog(
                contentText = "아이디 혹은 비밀번호가\n올바르지 않습니다.",
                text = "확인",
                onPress = { signInViewModel.changeDrawerState() },
                image = R.drawable.baseline_error_24
            )
        }
    }
}

@Composable
fun LoginImage() {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Row {
            Spacer(modifier = Modifier.width(32.dp))
            Image(
                painter = painterResource(id = R.drawable.balloon),
                contentDescription = "image description",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(331.dp),
                alignment = Alignment.TopEnd
            )
        }
    }
}
/*
@Composable
fun LoginTextFieldId(  // textfield를 하나만 만들고 이름만 바꿔서 함수를 재사용 할 수 있게 변경해주세요
    id: State<String>,
    onValueId: (String) -> Unit
) {
    LoginTextFieldID(
        text = id,
        text2 = "ID",
        onValueChange = onValueId
    )
}

@Composable
fun LoginTextFieldPw(  // textfield를 하나만 만들고 이름만 바꿔서 함수를 재사용 할 수 있게 변경해주세요
    password: State<String>,
    onValuePw: (String) -> Unit
) {
    LoginTextFieldPW(
        text = password,
        text2 = "PASSWORD",
        onValueChange = onValuePw
    )
}
*/
@Composable
fun LoginBtn(onPress: () -> Unit) { //onPress란 람다 함수를 추가시키세요
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            CustomButton(
                text = "로그인",
                enable = true,
                content = Color.White,
                back = Color(0xFF2D64D8),
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
            ) {
                onPress()
            }
        }
    }
}

@Composable
fun OnLoginFindIdAndPassword(navController: NavController) { //textbutton 이름만 바꿔서 재사용 할 수 있게 수정해주세요 widget폴더에다 만들고 불러오세요
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LoginFindIdAndPassword(navController)
        }
    }
}


@Composable
fun MadeAccountText() { // 디바이더 함수도 widget폴더에 만들고 불러와서 사용해주세요
    MadeAccount()
}

@Composable
fun SignUpBtn(navController: NavController) { // asdasd변수 이름 적절하게 바꿔주세여
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomButton(
                "회원가입",
                enable = true,
                Color.White,
                Color.Black,
                Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            ) {
                navController.navigate(Screen.SignUpScreen.route)
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun SignInPreview(navController: NavController = rememberNavController()) {
    val id: State<String> = mutableStateOf("미ㅑ녀휵ㅇ리ㅑㅛ뮾ㄴㄷ겨라ㅣㅛㅁㅍㅈ")
    val password: State<String> = mutableStateOf("")
    val focusRequesterID = remember { FocusRequester() }
    val focusRequesterPW = remember { FocusRequester() }
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        LoginImage()
        Spacer(modifier = Modifier.height(90.dp))
        Column (
            modifier = Modifier
                .padding(horizontal = 32.dp)
        ) {
            LoginTextFieldID(
                text = id,
                text2 = "ID",
                focusRequesterID,
                focusRequesterPW
            ) { }
            Spacer(modifier = Modifier.height(10.dp))
            LoginTextFieldPW(
                text = password,
                text2 = "PASSWORD",
                focusRequesterPW
            ) { }
            Spacer(modifier = Modifier.height(10.dp))
            LoginBtn() { }
            OnLoginFindIdAndPassword(navController)
            Spacer(modifier = Modifier.height(10.dp))
            MadeAccountText()
            SignUpBtn(navController)
        }
    }

}

/*@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(signInViewModel: SignInViewModel = viewModel()) {
    val viewModel = hiltViewModel<SignInViewModel>()
    val navController = rememberNavController() // NavController 초기화
    SignInScreen(viewModel,navController)
}


@Preview(showBackground = true)
@Composable
fun OnLoginBtnPreview(signInViewModel: SignInViewModel = viewModel()) {
    LoginBtn(){signInViewModel.postSignIn()}
}


@Preview(showBackground = true)
@Composable
fun OnLoginFindIdAndPasswordPreview(navController: NavController) {
    OnLoginFindIdAndPassword(navController)
}

@Preview(showBackground = true)
@Composable
fun MadeAccountTextPreview() {
    MadeAccountText()
}


@Preview(showBackground = true)
@Composable
fun OnSignUpBtnPreview() {
    val navController = rememberNavController()
    SignUpBtn(navController)
}


@Preview(showBackground = true)
@Composable
fun LoginTextFieldPreview() {
    val editidState = remember {
        mutableStateOf("")
    }
    val editpasswordState = remember {
        mutableStateOf("")
    }
    //LoginTextField(id = editidState, password = editpasswordState)
}


@Preview(showBackground = true)
@Composable
fun OnLoginImagePreview() {
    LoginImage()
}*/
