
package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignInViewModel
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.loginFindIdAndPassword
import com.example.usw_random_chat.ui.loginTextField
import com.example.usw_random_chat.ui.madeAccount


@Composable // 제가 만들어 놓은 viewmodel 함수를 적용해서 완벽한 signin 화면을 만들어주세요, 어려우면 profile 화면 참고!!
fun SignInScreen(signInViewModel: SignInViewModel = viewModel(),navController: NavController) {

    Box{
        LoginImage()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 310.dp
                )
        ) {
            LoginTextFieldId(id = signInViewModel.id) { signInViewModel.updateID(it) }
            Spacer(modifier = Modifier.height(8.dp))
            LoginTextFieldPW(password = signInViewModel.password) { signInViewModel.updatePassWord(it) }
        }
    }
    LoginBtn(signInValue = signInViewModel.signInValue.value,navController){signInViewModel.postSignIn()}
    OnLoginFindIdAndPassword(navController)
    MadeAccountText()
    SignUpBtn(navController)
}


@Composable
fun LoginImage() {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ){
        Row(){
            Spacer(modifier = Modifier.weight(0.1f))
            Image(
                painter = painterResource(id = R.drawable.balloon),
                contentDescription = "image description",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(331.dp)
                    .weight(1f),
                alignment = Alignment.TopEnd
            )
        }
    }
}

@Composable
fun LoginTextFieldId(  // textfield를 하나만 만들고 이름만 바꿔서 함수를 재사용 할 수 있게 변경해주세요
    id: State<String>,
    onValueId: (String) -> Unit
) {
    loginTextField(text = id, text2 = "ID", onValueChange = onValueId)
}

@Composable
fun LoginTextFieldPW(  // textfield를 하나만 만들고 이름만 바꿔서 함수를 재사용 할 수 있게 변경해주세요
    password: State<String>,
    onValuePw: (String) -> Unit
) {
    loginTextField(text = password, text2 = "PASSWORD",onValueChange = onValuePw)
}

@Composable
fun LoginBtn(signInValue : Boolean,navController: NavController,onPress: () -> Unit) { //onPress란 람다 함수를 추가시키세요
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 438.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        button(
            text = "로그인",
            enable = true,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .height(56.dp)
                .weight(1f),
        ){
            onPress()
            if(signInValue){
                navController.navigate(Screen.SignUpScreen.route)
            }
            else{

            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun OnLoginFindIdAndPassword(navController: NavController) { //textbutton 이름만 바꿔서 재사용 할 수 있게 수정해주세요 widget폴더에다 만들고 불러오세요
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 492.dp
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        loginFindIdAndPassword(navController)
    }
}


@Composable
fun MadeAccountText() { // 디바이더 함수도 widget폴더에 만들고 불러와서 사용해주세요
    madeAccount()
}

@Composable
fun SignUpBtn(navController: NavController) { // asdasd변수 이름 적절하게 바꿔주세여
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 599.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        button(
            "회원가입",
            enable = true,
            Color.White,
            Color.Black,
            Modifier
                .height(56.dp)
                .weight(1f)
        ){
            navController.navigate(Screen.EmailAuthScreen.route)
        }
        Spacer(modifier = Modifier.weight(0.1f))
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
