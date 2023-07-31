package com.example.usw_random_chat.Screen

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    val nickname = remember {
        mutableStateOf("")
    }
    val mbti = remember {
        mutableStateOf("")
    }
    val selfintroduce = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        setTitle()
        getNickName(nickname = nickname)
        getMBTI(mbti = mbti)
        getSelfIntroduce(introduce = selfintroduce)
        startButton()
        Text(
            text = "프로필은 언제든 자유롭게\n수정할 수 있습니다",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}

@Composable
fun setTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
    ) {
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
        }
        Text(
            text = "프로필 설정",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, end = 40.dp)
        )
    }
}

@Composable
fun getNickName(nickname: MutableState<String>) {
    Column(Modifier.padding(top = 40.dp)) {
        Row() {
            Text(text = "닉네임", fontSize = 16.sp)
            Text(
                text = "(필수)",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 3.dp, top = 2.dp)
            )
        }
        TextField(
            value = nickname.value,
            onValueChange = { nicknameValue -> nickname.value = nicknameValue },
            placeholder = { Text(text = "#NICKNAME") },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledLabelColor = Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFFBFBFBF), shape = RoundedCornerShape(8.dp))
                .height(50.dp)
                .width(326.dp)
        )
        Text(text = "* 닉네임은 8자 이내로 작성해 주세요", color = Color(0xFFFF6565), fontSize = 12.sp)
    }
}

@Composable
fun getMBTI(mbti: MutableState<String>) {
    Column(Modifier.padding(top = 10.dp)) {
        Row() {
            Text(text = "MBTI", fontSize = 16.sp)
            Text(
                text = "(선택)",
                color = Color.Gray,
                fontSize = 10.sp,
                modifier = Modifier.padding(start = 3.dp, top = 5.dp)
            )
        }
        TextField(
            value = mbti.value,
            onValueChange = { nicknameValue -> mbti.value = nicknameValue },
            placeholder = { Text(text = "#MBTI") },
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
fun getSelfIntroduce(introduce: MutableState<String>) {
    Column(Modifier.padding(top = 10.dp)) {
        Row() {
            Text(text = "자기소개", fontSize = 16.sp)
            Text(
                text = "(선택)",
                color = Color.Gray,
                fontSize = 10.sp,
                modifier = Modifier.padding(start = 3.dp, top = 5.dp)
            )
        }
        TextField(
            value = introduce.value,
            onValueChange = { nicknameValue -> introduce.value = nicknameValue },
            placeholder = { Text(text = "학과, 학번 등 소개를 자유롭게 입력하세요", fontSize = 14.sp) },
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
fun startButton() {
    Box(modifier = Modifier.padding(top = 50.dp)) {
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF2D64D8),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(326.dp)
                .height(56.dp)

        ) {
            Text(
                text = "시작하기",
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen()
}

@Preview(showBackground = true)
@Composable
fun getNickNamePreview() {
    val qwe = remember {
        mutableStateOf("#NICKNAME")
    }
    getNickName(qwe)
}

@Preview(showBackground = true)
@Composable
fun getMBTIPreview() {
    val qwe = remember {
        mutableStateOf("#MBTI")
    }
    getMBTI(qwe)
}

@Preview(showBackground = true)
@Composable
fun getSelfIntroducePreview() {
    val qwe = remember {
        mutableStateOf("#학과 학번등 소개를 자유롭게 해주세요")
    }
    getSelfIntroduce(introduce = qwe)
}

@Preview(showBackground = true)
@Composable
fun startButtonPreview() {
    val qwe = remember {
        mutableStateOf("#학과 학번등 소개를 자유롭게 해주세요")
    }
    startButton()
}

@Preview(showBackground = true)
@Composable
fun setTitlePreview() {
    setTitle()
}

