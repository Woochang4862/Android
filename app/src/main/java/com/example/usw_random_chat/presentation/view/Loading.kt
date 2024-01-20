package com.example.usw_random_chat.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignInViewModel
import kotlinx.coroutines.delay


@Composable
fun LoadingScreen(navController: NavController, signInViewModel: SignInViewModel = viewModel())  {

    LoadingLogo("심심할 땐,", "SUCHAT")


    LaunchedEffect(Unit) {
        delay(1000L)
        signInViewModel.autoSignIn()
        if (signInViewModel.loginState.value){
            navController.navigate(Screen.MainPageScreen.route){
                navController.popBackStack()
            }
        }
        else{
            navController.navigate(Screen.SignInScreen.route){
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun LoadingLogo(first: String, second: String) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFF5E8BFF))
        ,horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(0.4f))
        Text(
            text = first,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 18.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(24.dp)
                .weight(0.03f)
        )
        Row(Modifier.weight(0.5f)) {
            Spacer(Modifier.padding(5.dp))
            Text(
                text = second,
                fontFamily = FontFamily(Font(R.font.amberygardenregular)),
                fontSize = 36.sp,
                lineHeight = 200.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .height(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.speechbubble),
                contentDescription = null,
                modifier = Modifier
                    .size(44.dp, 43.dp)
                    .padding(start = 15.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(navController = rememberNavController())
}
