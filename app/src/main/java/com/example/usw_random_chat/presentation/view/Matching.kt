package com.example.usw_random_chat.presentation.view

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.ChatViewModel
import kotlinx.coroutines.delay

@Composable
fun MatchingScreen(navController: NavController, chatViewModel: ChatViewModel = viewModel()) {
    TextBlue()
    TextBlack(chatViewModel.textList.random())
    MatchingStopBtn{
        navController.popBackStack()
        chatViewModel.stopMatching()
    }
    MatchingAnimation()
    if (chatViewModel.matchingPresence.value){
        navController.navigate(Screen.ChatScreen.route) {
            navController.popBackStack()
        }
    }
}

@Composable
fun TextBlue(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 375.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "알고 계셨나요?",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                fontWeight = FontWeight(400),
                color = Color(0xFF2D64D8),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}


@Composable
fun TextBlack(ment : String){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 413.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = ment,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                fontWeight = FontWeight(400),
                color = Color(0xFF767676),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.weight(0.1f))
    }
}



@Composable
fun MatchingStopBtn(onPress: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 493.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        CustomButton(
            "매칭 중단하기",
            enable = true,
            Color.White,
            Color.Black,
            Modifier
                .weight(1f)
                .height(56.dp)
                .background(color = Color.White)
        ){
            onPress()
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun MatchingAnimation() {
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
    Box(
        modifier = Modifier
            .width(312.dp)
            .height(400.dp)
            .padding(
                start = 129.dp,
                top = 265.dp
            ),
    ) {
        AnimatedVisibility(
            visible = matchingBlank.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "매칭 중")
        }
        AnimatedVisibility(
            visible = matchingDot1.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "매칭 중.")
        }
        AnimatedVisibility(
            visible = matchingDot2.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "매칭 중..")
        }
        AnimatedVisibility(
            visible = matchingDot3.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MatchingAnimationText(text = "매칭 중...")
        }
        AnimatedVisibility(
            visible = matchingDot4.value,
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
    TextBlue()
    TextBlack("asdasd")
    MatchingStopBtn{}
    MatchingAnimation(
    )
}