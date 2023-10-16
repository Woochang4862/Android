package com.example.usw_random_chat.Screen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.portalEmail

@Composable
fun IdSearch(navController: NavController){
    var editTextState = remember {
        mutableStateOf("")
    }
    val openFlag = remember {
        mutableStateOf(false)
    }
    if (editTextState.value.length>6){
        openFlag.value = true
    }
    if (editTextState.value.length<=6){
        openFlag.value = false
    }
    IdSearchText()
    IdSearchEmail(textState = editTextState)
    IdSearchEmailBtn()
    Checkemail(openFlag.value)
    GoLogin()
    IdSearchExitBtn()
}


@Composable
fun IdSearchExitBtn(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 80.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "image description",
            modifier = Modifier
                .height(36.dp)
                .weight(0.2f)
        )
        Spacer(modifier = Modifier.weight(1.1f))
    }
}

@Composable
fun IdSearchText(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 88.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "아이디 찾기",
            color = Color(0xFF111111),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular))
            ),
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier
                .weight(0.6f)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun IdSearchEmail(textState: MutableState<String>) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 143.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        portalEmail(
            textFieldValue = textState.value, onValueChange = { newValue ->
                textState.value = newValue
            })
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun IdSearchEmailBtn(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 256.dp
            ),
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF989898))) {
                    append("*입력하신 메일로 ")
                }
                withStyle(style = SpanStyle(color = Color(0xFF2D64D8))) {
                    append("가입된 아이디 ")
                }
                withStyle(style = SpanStyle(color = Color(0xFF989898))){
                    append("정보를 전송합니다")
                }
            },
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 12.sp,
            color = Color(0xFFDCDCDC),
            modifier = Modifier
                .height(18.dp)
                .weight(1f)
        )
        Spacer(modifier = Modifier.weight(0.3f))
    }
}

@Composable
fun Checkemail(flag: Boolean){

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 286.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        button(
            text = "확인메일 전송",
            enable = flag,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
        ) {

        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}


@Composable
fun GoLogin(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 354.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        button(
            "로그인 하러가기",
            enable = true,
            Color.White,
            Color.Black,
            Modifier
                .weight(1f)
                .height(56.dp)
                .background(color = Color.White)
        ){

        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}



@Preview (showBackground = true)
@Composable
fun IdSearchPreview(){
    IdSearch(navController = rememberNavController())
}

@Preview (showBackground = true)
@Composable
fun IdSearchTextPreview(){
    IdSearchText()
}


@Preview (showBackground = true)
@Composable
fun IdSearchExitBtnPreview() {
    IdSearchExitBtn()
}
@Preview (showBackground = true)
@Composable
fun IdSearchEmailPreview() {

    val editTextState = remember {
        mutableStateOf("")
    }
    IdSearchEmail(editTextState)
}