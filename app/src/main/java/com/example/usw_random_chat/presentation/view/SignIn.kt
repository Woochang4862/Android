
package com.example.usw_random_chat.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignInViewModel
import com.example.usw_random_chat.ui.OneButtonDialog
import com.example.usw_random_chat.ui.CustomButton
import com.example.usw_random_chat.ui.LoginFindIdAndPassword
import com.example.usw_random_chat.ui.LoginTextFieldID
import com.example.usw_random_chat.ui.LoginTextFieldPW
import com.example.usw_random_chat.ui.MadeAccount


@Composable // 제가 만들어 놓은 viewmodel 함수를 적용해서 완벽한 signin 화면을 만들어주세요, 어려우면 profile 화면 참고!!
fun SignInScreen(signInViewModel: SignInViewModel = viewModel(),navController: NavController) {

    LoginImage()
    LoginTextFieldId(
        id = signInViewModel.id)
        { newId -> signInViewModel.updateID(newId) }
    LoginTextFieldPw(
        password = signInViewModel.password
    ) { signInViewModel.updatePassWord(it) }
    LoginBtn(){signInViewModel.postSignIn()}
    OnLoginFindIdAndPassword(navController)
    MadeAccountText()
    SignUpBtn(navController)

    if (signInViewModel.loginState.value){
        navController.navigate(Screen.MainPageScreen.route){
            navController.popBackStack()
        }
        signInViewModel.changeLoginState()
    }
    if(signInViewModel.dialogState.value){
        OneButtonDialog(
            contentText = "아이디 혹은 비밀번호가\n올바르지 않습니다.",
            text = "확인",
            onPress = { signInViewModel.changeDrawerState() },
            image = R.drawable.baseline_error_24
        )
    }
}

@Composable
fun LoginImage() {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ) {
        Row() {
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
@Composable
fun LoginBtn(onPress: () -> Unit) { //onPress란 람다 함수를 추가시키세요
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ){
        Spacer(modifier = Modifier.weight(4.9f))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            CustomButton(
                text = "로그인",
                enable = true,
                content = Color.White,
                back = Color(0xFF2D64D8),
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f),
            ) {
                onPress()
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
        Spacer(modifier = Modifier.weight(2.2f))
    }
}

@Composable
fun OnLoginFindIdAndPassword(navController: NavController) { //textbutton 이름만 바꿔서 재사용 할 수 있게 수정해주세요 widget폴더에다 만들고 불러오세요
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ){
        Spacer(modifier = Modifier.weight(5.2f))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f),
            horizontalArrangement = Arrangement.Center
        ) {
            LoginFindIdAndPassword(navController)
        }
        Spacer(modifier = Modifier.weight(1.8f))
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
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(6.4f))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            CustomButton(
                "회원가입",
                enable = true,
                Color.White,
                Color.Black,
                Modifier
                    .height(56.dp)
                    .weight(1f)
            ) {
                navController.navigate(Screen.SignUpScreen.route)
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
        Spacer(modifier = Modifier.weight(0.6f))
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun SignInPreview(navController: NavController = rememberNavController()) {
    val id : State<String> = mutableStateOf("")
    val password : State<String>  = mutableStateOf("")

    LoginImage()
    LoginTextFieldId(id)
    {  }
    LoginTextFieldPw(password) {  }
    LoginBtn(){}
    OnLoginFindIdAndPassword(navController)
    MadeAccountText()
    SignUpBtn(navController)
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
