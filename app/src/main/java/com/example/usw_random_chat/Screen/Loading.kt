package com.example.usw_random_chat.Screen

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usw_random_chat.R
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(){
    val visible = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit){
        delay(700L)
        visible.value = true
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString{
                append("포털 메일로 ")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("가입 승인 링크")
                }
                append("를 발송 했습니다.")
            },
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF111111),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(48.dp)
                .width(232.dp)

        )
        AnimatedVisibility(
            visible = visible.value,
            enter = expandIn(),
            exit = shrinkOut()
        ){
            Image(
                painter = painterResource(id = R.drawable.baseline_check_circle_24),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .padding(0.dp)
                    .width(100.dp)
                    .height(160.dp)
            )
        }

        Text(
            text = buildAnnotatedString{
                append("발송 된 링크 ")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("클릭 ")
                }
                append("후 로그인 해주세요")
            },
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF111111),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(114.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF2D64D8),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .width(326.dp)
                .height(56.dp)
                .background(color = Color.White)
        ) {
            Text(
                text = "로그인",
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
            )
        }

    }

}

@Preview (showBackground = true)
@Composable
fun LoadingScreenPreview(){
    LoadingScreen()
}