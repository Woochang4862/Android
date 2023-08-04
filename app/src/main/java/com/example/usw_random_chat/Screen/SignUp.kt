package com.example.usw_random_chat.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usw_random_chat.R

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {

        title()

        Column(Modifier.padding(15.dp)
        ){
            TextFieldWithButton("아이디 입력 (4~16자)","아이디", "*4자 이상 16자 이내로 작성해주세요")
            Spacer(Modifier.padding(15.dp))
            TextFieldWithIcon( "비밀번호 입력 (문자,숫자 포함 6~20자)", "비밀번호", "*6자 이상 20자 이내로 작성해 주세요" )
            TextFieldWithIcon("","비밀번호 확인", "*비밀번호가 일치하지 않습니다")
        }
        EmailTextField("* 이메일 형식이 올바르지 않습니다")
        signUpBotton()
    }
}



@Composable
fun title(){
    Row(Modifier
        ,horizontalArrangement = Arrangement.Center)
    {
        Spacer(Modifier.width(16.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
        }
        Text(
            text = buildAnnotatedString {
                append("회원가입")
            },
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(48.dp)
                .width(232.dp)
                .padding(start = 65.dp)
        )
    }
}

@Composable
fun TextFieldWithButton(inWord:String,
                        name: String,
                        subname: String) {
    var text = remember { mutableStateOf("") }

    Row(
        Modifier
        ,horizontalArrangement = Arrangement.Start

    ) {
        Text(
            text = name,
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 10.dp)

        )
        Text(
            text = subname,
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFF0000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = text.value,
            onValueChange = { newText ->
                text.value = newText
            },
            placeholder = { Text(text = inWord, color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
                unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
                disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
            ),
            // shape 속성 주석 처리
            // shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        Button(
            onClick = { /* Do something when the button is clicked */ },
            modifier = Modifier.padding(10.dp)
                .align(Alignment.CenterVertically)
                .width(100.dp)
                .height(38.dp),

            shape = RoundedCornerShape(10.dp)
            ,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF2D64D8)
            ),

            ) {
            Text(text = "중복 확인")
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcon(
    inWord: String,
    name: String, subname: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 17.sp
)
{
    var text = rememberSaveable {
        mutableStateOf("")
    }
    Row(
        Modifier
        , horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = name,
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 10.dp)

        )
        Text(
            text = subname,
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFF0000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
        )
    }

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ){
        TextField(
            value = text.value,
            onValueChange = { textValue -> text.value = textValue },
            placeholder = { Text(text = inWord, color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
                unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
                disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .height(50.dp)
                .width(300.dp)
        )//baseline_visibility_24
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.visibility),
                contentDescription = null // decorative element

            )
        }
    }
}



@Composable
fun EmailTextField(name: String) {
    var textState = rememberSaveable { mutableStateOf("") }

    Column(Modifier.padding(30.dp)

    ) {
        TextField(
            value = textState.value,
            onValueChange = {textStateValue -> textState.value = textStateValue},
            placeholder = { Text("포털 이메일 입력") },
            trailingIcon = { Text("@suwon.ac.kr    ", color = Color(0xFF000000)) },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            modifier = Modifier
                .width(326.dp)
                .heightIn(min = 56.dp)

        )
        Text(
            text = name,
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFF0000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(top = 10.dp)
        )
    }
}

@Composable
fun signUpBotton(){
    Column(Modifier.padding(30.dp)
        ,horizontalAlignment = Alignment.CenterHorizontally) {
        Button(modifier = Modifier
            .width(326.dp)
            .height(56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color.Black
            ),
            onClick = {}
        ) {
            Text("회원가입")
        }

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("회원가입 시  ")
                }
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("서비스 이용약관 ")
                }
                withStyle(style = SpanStyle(color = Color.Gray)){
                    append(" 및 ")
                }
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("개인정보 처리방침")
                }
                withStyle(style = SpanStyle(color = Color.Gray)){
                    append("에 동의하신 것으로 간주됩니다")
                }
            },
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFDCDCDC),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(50.dp)
                .width(273.dp)
                .padding(3.dp,top = 10.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}