package com.example.usw_random_chat.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignInViewModel
import com.example.usw_random_chat.presentation.ViewModel.SignUpViewModel
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.portalEmail
import com.example.usw_random_chat.ui.sendImg
import com.example.usw_random_chat.ui.text
import com.example.usw_random_chat.ui.tittleWithBackArrow

@Composable
fun EmailAuthScreen(signUpViewModel: SignUpViewModel = viewModel(), navController: NavController){
    SignUpEmail(email = signUpViewModel.email){ signUpViewModel.updateEmail(it) }
    SignUpEmailBtn()
    RequestEmail{signUpViewModel.verifyEmail()}
    NextBtn(verifyFlag = signUpViewModel.verifyFlag.value){navController.navigate(Screen.SignUpScreen.route)}
    SignUpExitBtn{navController.popBackStack()}
}


@Composable
fun SignUpExitBtn(onPress: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 60.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        tittleWithBackArrow(
            "회원가입",
            Modifier
                .height(48.dp)
                .width(100.dp)
                .weight(0.6f)
                .offset(y = 10.dp),
            onBackClick = { onPress }
        )
        Spacer(modifier = Modifier.weight(1.1f))
    }
}


@Composable
fun SignUpEmail(
    email: State<String>,
    onValueEmail: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 143.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        portalEmail(textFieldValue = email, onValueChange = onValueEmail)
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun SignUpEmailBtn(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 256.dp
            ),
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        text(
            text1 = "*입력하신 메일로 ",
            text2 = "이메일 인증 URL",
            text3 = "을 전송합니다",
            modifier = Modifier
                .height(18.dp)
                .weight(1f))
        Spacer(modifier = Modifier.weight(0.3f))
    }
}

@Composable
fun RequestEmail(onPress: () -> Unit){

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 286.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        button(
            text = "인증메일 전송",
            enable = true,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
        ) {
            onPress()
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}


@Composable
fun NextBtn(verifyFlag: Boolean, onPress: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 354.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        button(
            "다음",
            enable = verifyFlag,
            Color.White,
            Color.Black,
            Modifier
                .weight(1f)
                .height(56.dp)
                .background(color = Color.White)
        ){
            onPress
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}



@SuppressLint("UnrememberedMutableState")
@Preview (showBackground = true)
@Composable
fun EmailAuthScreenPreview(){
    val asd : State<String> = mutableStateOf("")
    SignUpEmail(email = asd){}
    SignUpEmailBtn()
    RequestEmail{}
    NextBtn(verifyFlag = true){}
    SignUpExitBtn{}
}


/*@Preview (showBackground = true)
@Composable
fun SignUpExitBtnPreview(navController: NavController) {
    SignUpExitBtn(navController)
}
@Preview (showBackground = true)
@Composable
fun SignUpEmailPreview() {

    val TextState = remember {
        mutableStateOf("")
    }
    IdSearchEmail(TextState)
}*/