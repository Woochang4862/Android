package com.example.usw_random_chat.Screen

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpDoneScreen(navController: NavController) {
    val visibleCircle = remember {
        mutableStateOf(false)
    }
    val visibleCheck = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(500L)
        visibleCircle.value = true
        delay(500L)
        visibleCheck.value = true
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString {
                append("포털 메일로 ")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("가입 승인 링크")
                }
                append("를 발송 했습니다.")
            },
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF111111),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(48.dp)
                .width(202.dp)

        )
        Animation(visible1 = visibleCircle.value, visible2 = visibleCheck.value)

        Text(
            text = buildAnnotatedString {
                append("발송 된 링크 ")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("클릭 ")
                }
                append("후 로그인 해주세요")
            },
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF111111),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        Row {
            Spacer(modifier = Modifier.weight(0.1f))
            button(
                text = "로그인",
                enable = true,
                content = Color.Black,
                back = Color.White,
                modifier = Modifier
                    .padding(top = 126.dp)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF2D64D8),
                    )
                    .weight(1f)
                    .height(56.dp)
                    .background(color = Color.White)){
                navController.navigate(Screen.SignInScreen.route){
                    popUpTo(Screen.SignInScreen.route){
                        inclusive = true
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }

        button(
            "메일 재발송",
            enable = true,
            Color.White,
            Color.Black,
            Modifier
                .padding(top = 12.dp)
                .width(326.dp)
                .height(56.dp)
                .background(color = Color.White)
        ){

        }
    }
}

@Composable
fun Animation(visible1: Boolean,visible2: Boolean) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(160.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible1,
            enter = fadeIn()
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_circle_24),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .padding(0.dp)
                    .width(100.dp)
                    .height(160.dp)
            )
        }
        AnimatedVisibility(
            visible = visible2,
            enter = fadeIn()
        ) {
            Image(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .padding(0.dp)
                    .width(100.dp)
                    .height(160.dp)
            )
        }

    }

}



@Preview(showBackground = true)
@Composable
fun SignUpDoneScreenPreview() {
    SignUpDoneScreen(navController = rememberNavController())
}