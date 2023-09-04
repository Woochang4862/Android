package com.example.usw_random_chat.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
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
    IdSearchEmailBtn(openFlag.value)
    IdSearchExitBtn()
}


@Composable
fun IdSearchExitBtn(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 32.dp,
                top = 80.dp
            )
    ){
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "image description",
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)
        )
    }
}

@Composable
fun IdSearchText(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 155.dp,
                top = 88.dp
            )
    ) {
        Text(
            text = "아이디 찾기",
            color = Color(0xFF111111),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular))
            ),
            fontSize = 18.sp,
            fontWeight = FontWeight(600)
        )
    }
}

@Composable
fun IdSearchEmail(textState: MutableState<String>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 31.dp,
                top = 143.dp
            )
    ) {
        portalEmail(
            textFieldValue = textState.value, onValueChange = { newValue ->
                textState.value = newValue
            })
    }
}

@Composable
fun IdSearchEmailBtn(flag: Boolean){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 256.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                .width(242.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        button(
            text = "확인메일 전송",
            enable = flag,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .width(326.dp)
                .height(56.dp)
        ){

        }
        Spacer(modifier = Modifier.height(12.dp))

        button(
            "로그인 하러가기",
            enable = true,
            Color.White,
            Color.Black,
            Modifier
                .width(326.dp)
                .height(56.dp)
                .background(color = Color.White)
        ){

        }
    }
}





@Preview (showBackground = true)
@Composable
fun IdSerchPreview(){
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