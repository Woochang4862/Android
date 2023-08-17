package com.example.usw_random_chat.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.button

@Composable
fun PwChangeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {

        Spacer(Modifier.padding(20.dp))

        titleOfPwChange()

        Spacer(Modifier.padding(15.dp))

        TextFieldOfPwChange("새 비밀번호 입력 (문자,숫자 포함 6~20자)", "비밀번호", "* 6자 이상 20자 이내로 작성해 주세요")
        Spacer(Modifier.padding(5.dp))

        TextFieldOfPwChange("", "비밀번호 확인", "* 비밀번호가 일치하지 않습니다")

        Spacer(Modifier.padding(10.dp))
        PwChangeBotton()
    }
}


@Composable
fun titleOfPwChange() {
    Row(
        Modifier, horizontalArrangement = Arrangement.Center
    )
    {
        Spacer(Modifier.width(32.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "",
                Modifier
                    .height(36.dp)
                    .width(36.dp)
            )
        }
        Text(
            text = buildAnnotatedString {
                append("비밀번호 변경")
            },
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF111111),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(48.dp)
                .width(232.dp)
                .padding(start = 32.dp, top = 10.dp)
        )
    }
}


@Composable
fun TextFieldOfPwChange(
    inWord: String,
    name: String,
    subname: String
) {
    var text = remember { mutableStateOf("") }

    Row(
        Modifier, horizontalArrangement = Arrangement.Start

    ) {
        Text(
            text = name,
            fontFamily = FontFamily.SansSerif,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 48.dp)

        )
        Text(
            text = subname,
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFF0000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 12.dp, top = 3.dp)
        )
    }

    Spacer(Modifier.padding(1.dp))

    TextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
        },

        placeholder = { Text(text = inWord, color = Color.Gray, fontSize = 14.sp) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
            unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
            disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
        ),
        // shape 속성 주석 처리
        // shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(368.dp)
            .height((50.dp))
            .padding(start = 42.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    )

}

@Composable
fun PwChangeBotton() {
    Column(
        Modifier.padding(42.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        button(
            text = "변경완료",
            enable = true,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .width(326.dp)
                .height(56.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PwChangeScreenPreview() {
    PwChangeScreen()
}