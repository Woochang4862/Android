package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

@Composable
fun EditProfileScreen(navController: NavController) {
    val editNickName = remember {
        mutableStateOf("")
    }
    val editMBTI = remember {
        mutableStateOf("")
    }
    val editSelfIntroduce = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        editSetTitle()
        editGetNickName(nickname = editNickName)
        editGetMBTI(mbti = editMBTI)
        editGetSelfIntroduce(introduce = editSelfIntroduce)
        SuChatImg()
    }
}

@Composable
fun editSetTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
    ) {
        Text(
            text = "프로필 설정",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 155.dp)

        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(start = 107.dp)
        ) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = "check", tint = Color.Gray)
        }
    }
}

@Composable
fun editGetNickName(nickname: MutableState<String>) {
    Column(Modifier.padding(top = 40.dp)) {
        Text(
            text = "닉네임",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 38.dp, bottom = 5.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 32.dp, end = 32.dp)
                .border(
                    width = 1.dp, color = Color(0xFFBFBFBF),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            TextField(
                value = nickname.value,
                onValueChange = { newText ->
                    nickname.value = newText
                },
                placeholder = { Text(text = "#NICKNAME", color = Color.Gray) },
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
                    .width(326.dp)
                    .height(50.dp)
            )
            Button(
                onClick = { /* Do something when the button is clicked */ },
                modifier = Modifier
                    .padding(6.dp)
                    .align(Alignment.CenterVertically)
                    .width(100.dp)
                    .height(38.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color(0xFF2D64D8)
                ),
            ) {
                Text(
                    "중복 확인",
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Text(
            text = "* 닉네임은 8자 이내로 작성해 주세요",
            color = Color(0xFFFF6565),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier.padding(start = 36.dp, top = 5.dp)
        )
    }
}

@Composable
fun editGetMBTI(mbti: MutableState<String>) {
    Column(Modifier.padding(top = 5.dp)) {
        Row() {
            Text(
                text = "MBTI",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
            )
        }
        TextField(
            value = mbti.value,
            onValueChange = { nicknameValue -> mbti.value = nicknameValue },
            placeholder = {
                Text(
                    text = "#MBTI",
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledLabelColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFFBFBFBF), shape = RoundedCornerShape(8.dp))
                .height(50.dp)
                .width(326.dp)

        )
    }
}

@Composable
fun editGetSelfIntroduce(introduce: MutableState<String>) {
    Column(Modifier.padding(top = 10.dp)) {
        Row() {
            Text(
                text = "자기소개",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
            )
        }
        TextField(
            value = introduce.value,
            onValueChange = { nicknameValue -> introduce.value = nicknameValue },
            placeholder = {
                Text(
                    text = "학과, 학번 등 소개를 자유롭게 입력하세요(40자 이내)",
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 14.sp
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledLabelColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFFBFBFBF), shape = RoundedCornerShape(8.dp))
                .height(90.dp)
                .width(326.dp)
        )
    }
}

@Composable
fun SuChatImg() {
    Column(
        Modifier.padding(top = 252.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.suchat),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(85.dp)
                .height(18.dp)
        )
        Text(
            text = "@copyright by Flag",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 12.dp),
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF767676)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun SuChatImgPreview() {
    SuChatImg()
}