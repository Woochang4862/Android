package com.example.usw_random_chat.Screen

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.usw_random_chat.R
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navController: NavController) {
    LaunchedEffect(Unit){
        delay(1000L)
        navController.popBackStack()
        navController.navigate(Screen.SignInScreen.route)

    }

    val backgroundImage: Painter = painterResource(id = R.drawable.loadingscreen)
    val kccChassamFontFamily = FontFamily(
        Font(R.font.kcc_chassam)
    )
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "심심한 사람",
            style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 38.sp,
                fontFamily = kccChassamFontFamily,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
            ),
            modifier = Modifier
                .offset(x = 32.dp, y = 210.dp)
        )
        ColoredRectangle(
            Modifier
                .width(177.dp)
                .height(7.dp)
                .offset(x = 32.dp, y = 263.dp)
        )

        Text(
            text = "다 모여.",
            style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 38.sp,
                fontFamily = kccChassamFontFamily,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
            ),
            modifier = Modifier
                .offset(x = 32.dp, y = 267.dp)
        )

        ColoredRectangle(
            Modifier
                .width(125.dp)
                .height(7.dp)
                .offset(x = 32.dp, y = 317.dp)
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .padding(bottom = 137.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.suchat),
                contentDescription = null,
                modifier = Modifier
                    .width(76.dp)
                    .height(20.dp),
            )


            Text(
                text = "@copyright by Flag",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    letterSpacing = 0.3.sp,
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
    }
}
@Composable
fun ColoredRectangle(modifier: Modifier) {
    Canvas(
        modifier = modifier
    ) {
        drawRect(
            color = Color(0xFF93BEFF),
            size = size
        )
    }
}




@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    //LoadingScreen()
}