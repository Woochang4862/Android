package com.example.usw_random_chat.Screen

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.GetScreenWidthInDp
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.tittleWithBackArrow


@Composable
fun PwChangeScreen(navController: NavController) {

    val screenWidthInDp = (GetScreenWidthInDp() - 326)/2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {

        Spacer(Modifier.padding(20.dp))

        //titleOfPwChange()
        tittleWithBackArrow("비밀번호 변경",modifier = Modifier
            .height(48.dp)
            .width(200.dp)
            .padding(start = screenWidthInDp.dp,top = 15.dp))

        Spacer(Modifier.padding(15.dp))

        TextFieldOfPwChange("새 비밀번호 입력 (문자,숫자 포함 6~20자)", "비밀번호", "* 6자 이상 20자 이내로 작성해 주세요")
        Spacer(Modifier.padding(5.dp))

        TextFieldOfPwChange("", "비밀번호 확인", "* 비밀번호가 일치하지 않습니다")

        Spacer(Modifier.padding(10.dp))
        PwChangeBotton(navController = navController)
    }
}

@Composable
fun TextFieldOfPwChange(
    inWord: String,
    name: String,
    subname: String
) {
    var text = remember { mutableStateOf("") }
    val screenWidthInDp = (GetScreenWidthInDp() - 326)/2
    Row(
        Modifier, horizontalArrangement = Arrangement.Start

    ) {
        Text(
            text = name,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = (screenWidthInDp+5).dp)

        )
        Text(
            text = subname,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
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

        placeholder = { Text(text = inWord, color = Color.Gray, fontSize = 14.sp)},
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
            .padding(start = screenWidthInDp.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    )

}

@Composable
fun PwChangeBotton( navController: NavController) {
    val screenWidthInDp = (GetScreenWidthInDp() - 326)/2
    Column(
        Modifier.padding(screenWidthInDp.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        button(
            text = "변경완료",
            enable = true,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .width(326.dp)
                .height(56.dp)
        ){
            navController.navigate(Screen.SignInScreen.route)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PwChangeScreenPreview() {
    PwChangeScreen(navController = rememberNavController())
}