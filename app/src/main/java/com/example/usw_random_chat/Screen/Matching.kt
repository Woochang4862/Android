package com.example.usw_random_chat.Screen

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
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
import androidx.compose.ui.text.TextStyle
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
import com.example.usw_random_chat.ui.MatchingAnimationText
import com.example.usw_random_chat.ui.button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MatchingScreen(navController: NavController) {
    val matchingBlank = remember {
        mutableStateOf(false)
    }
    val matchingDot1 = remember {
        mutableStateOf(false)
    }
    val matchingDot2 = remember {
        mutableStateOf(false)
    }
    val matchingDot3 = remember {
        mutableStateOf(false)
    }
    val matchingDot4 = remember {
        mutableStateOf(false)
    }
    if(!matchingDot4.value){
        LaunchedEffect(Unit) {
            matchingBlank.value = true
            delay(1000L)
            matchingDot1.value = true
            delay(1000L)
            matchingDot2.value = true
            delay(1000L)
            matchingDot3.value = true
            delay(1000L)
            matchingDot4.value = true
        }
    }
    if(matchingDot4.value){
        matchingBlank.value = false
        matchingDot1.value = false
        matchingDot2.value = false
        matchingDot3.value = false
        matchingDot4.value = false
    }
    MatchingAnimation(
        screen1 = matchingBlank.value,
        screen2 = matchingDot1.value,
        screen3 = matchingDot2.value,
        screen4 = matchingDot3.value,
        screen5 = matchingDot4.value
    )
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 375.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "알고 계셨나요?",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                fontWeight = FontWeight(400),
                color = Color(0xFF2D64D8),
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "체대옆 공터는 원래 방송반이 있던 큰 건물이었습니다.",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                fontWeight = FontWeight(400),
                color = Color(0xFF767676),
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(60.dp))
        button(
            "매칭 중단하기",
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
fun MatchingAnimation(screen1: Boolean, screen2: Boolean, screen3: Boolean, screen4:Boolean, screen5:Boolean) {
    Box(
        modifier = Modifier
            .width(312.dp)
            .height(345.dp)
            .padding(
                start = 129.dp,
                top = 295.dp
            ),
    ) {
        AnimatedVisibility(
            visible = screen1,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "매칭 중")
        }
        AnimatedVisibility(
            visible = screen2,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "매칭 중.")
        }
        AnimatedVisibility(
            visible = screen3,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "매칭 중..")
        }
        AnimatedVisibility(
            visible = screen4,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "매칭 중...")
        }
        AnimatedVisibility(
            visible = screen5,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MatchingScreenPreview() {
    MatchingScreen(navController = rememberNavController())
}