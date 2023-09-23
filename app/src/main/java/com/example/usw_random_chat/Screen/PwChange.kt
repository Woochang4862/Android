package com.example.usw_random_chat.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    val screenWidthInDp = (GetScreenWidthInDp()-100)/2

    val rememberPW = remember {
        mutableStateOf("")
    }

    val rememberPWCheck = remember {
        mutableStateOf("")
    }

    val rememberPwEqualOrNot = remember{
        mutableStateOf(false)
    }
    rememberPwEqualOrNot.value = rememberPW.value == rememberPWCheck.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {

        Spacer(Modifier.padding(20.dp))

        tittleWithBackArrow("비밀번호 변경",modifier = Modifier
            .height(48.dp)
            .width(100.dp)
            .offset(y = 10.dp)
            .weight(0.6f)

        )

        Spacer(Modifier.padding(15.dp))

        TextFieldOfPwChange("새 비밀번호 입력 (문자,숫자 포함 6~20자)", "비밀번호", "* 6자 이상 20자 이내로 작성해 주세요",pw =rememberPW )
        Spacer(Modifier.padding(10.dp))

        TextFieldOfPwCheck("", "비밀번호 확인", "* 비밀번호가 일치하지 않습니다",pwcheck = rememberPWCheck, equal = rememberPwEqualOrNot.value)

        Spacer(Modifier.padding(20.dp))
        PwChangeBotton(navController = navController)
    }
}

@Composable
fun TextFieldOfPwChange(
    inWord: String,
    name: String,
    subname: String,
    pw : MutableState<String>
) {
    Row(
        Modifier, //horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.15f))
        Text(
            text = name,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier.height(19.dp).weight(0.24f)

        )
        if(pw.value.length < 6 || pw.value.length>20) {
            Text(
                text = subname,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF0000),
                textAlign = TextAlign.Left,
                modifier = Modifier.height(18.dp).weight(0.8f).padding(top=3.dp)
            )
            Spacer(Modifier.weight(0.3f))
        }
    }

    Spacer(Modifier.padding(5.dp))

    Row(Modifier){
        Spacer(Modifier.weight(0.1f))
        TextField(
            value = pw.value,
            onValueChange = { newPw ->
                pw.value = newPw
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
                .weight(1f)
                .height((50.dp))
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
                .border(
                    width = 1.dp, color = Color(0xFFBFBFBF),
                    shape = RoundedCornerShape(8.dp)
                )
        )
        Spacer(Modifier.weight(0.1f))
    }
}


@Composable
fun TextFieldOfPwCheck(
    inWord: String,
    name: String,
    subname: String,
    pwcheck : MutableState<String>,
    equal : Boolean
) {


    Row(
        Modifier, //horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.165f))
        Text(
            text = name,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier.height(19.dp).weight(0.4f)
        )
        if(equal) {
            Text(
                text = subname,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF0000),
                textAlign = TextAlign.Left,
                modifier = Modifier.height(18.dp).weight(0.8f).padding(top=3.dp)
            )
            Spacer(Modifier.weight(0.3f))
        }
    }

    Spacer(Modifier.padding(5.dp))

    Row(Modifier){
        Spacer(Modifier.weight(0.1f))
        TextField(
        value = pwcheck.value,
        onValueChange = { newPwcheck ->
            pwcheck.value = newPwcheck
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
            //.width(368.dp)
            .weight(1f)
            .height((50.dp))
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
        )
        Spacer(Modifier.weight(0.1f))
    }
}
@Composable
fun PwChangeBotton( navController: NavController) {
    Row(
        Modifier
    ) {
        Spacer(Modifier.weight(0.1f))
        button(
            text = "변경완료",
            enable = true,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                //.width(326.dp)
                .weight(1f)
                .height(56.dp)
        ){
            navController.navigate(Screen.SignInScreen.route)
        }
        Spacer(Modifier.weight(0.1f))
    }

}


@Preview(showBackground = true)
@Composable
fun PwChangeScreenPreview() {
    PwChangeScreen(navController = rememberNavController())
}