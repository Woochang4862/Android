package com.example.usw_random_chat.Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ViewModel.ProfileViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = viewModel()) {
   // val nickname = remember {
        //mutableStateOf(profileViewModel.nickname)
    //}
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
        getNickName(profileViewModel.nickname) { profileViewModel.updateNickname(it) }
        getMBTI(mbti = profileViewModel.mbti)
        getSelfIntroduce(introduce = profileViewModel.selfintroduce.value)
        startButton()
        Text(
            text = "프로필은 언제든 자유롭게\n수정할 수 있습니다",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 15.dp),
            fontFamily = FontFamily(Font(R.font.kcc_chassam))
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
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, end = 40.dp)
        )
    }
}

@Composable
fun getNickName(nickname: State<String>, onNicknameChanged: (String) -> Unit) {
    Column(Modifier.padding(top = 40.dp)) {
        Row() {
            Text(text = "닉네임", fontSize = 16.sp)
            Text(
                text = "(필수)",
                color = Color.Red,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                modifier = Modifier.padding(start = 3.dp, top = 2.dp)
            )
        }
        TextField(
            value = nickname.value,
            onValueChange = onNicknameChanged,
            placeholder = { Text(text = "#NICKNAME",fontFamily = FontFamily(Font(R.font.pretendard_regular)),) },
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
        Text(text = "* 닉네임은 8자 이내로 작성해 주세요", color = Color(0xFFFF6565), fontSize = 12.sp,fontFamily = FontFamily(Font(R.font.pretendard_regular)),)
    }
}

@Composable
fun getMBTI(mbti: State<String>) {
    Column(Modifier.padding(top = 10.dp)) {
        Row() {
            Text(text = "MBTI", fontSize = 16.sp,fontFamily = FontFamily(Font(R.font.pretendard_regular)))
            Text(
                text = "(선택)",
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                color = Color.Gray,
                fontSize = 10.sp,
                modifier = Modifier.padding(start = 3.dp, top = 5.dp)
            )
        }
        TextField(
            value = mbti.value,
            onValueChange = { mbti},
            placeholder = { Text(text = "#MBTI",fontFamily = FontFamily(Font(R.font.pretendard_regular)),) },
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
fun getSelfIntroduce(introduce: String) {
    Column(Modifier.padding(top = 10.dp)) {
        Row() {
            Text(text = "자기소개", fontSize = 16.sp,fontFamily = FontFamily(Font(R.font.pretendard_regular)),)
            Text(
                text = "(선택)",
                color = Color.Gray,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                modifier = Modifier.padding(start = 3.dp, top = 5.dp)
            )
        }
        TextField(
            value = introduce,
            onValueChange = { introduce},
            placeholder = { Text(text = "학과, 학번 등 소개를 자유롭게 입력하세요",fontFamily = FontFamily(Font(R.font.pretendard_regular)), fontSize = 14.sp) },
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
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(profileViewModel = ProfileViewModel())
}

@Preview(showBackground = true)
@Composable
fun getNickNamePreview() {
    val qwe = remember {
        mutableStateOf("#NICKNAME")
    }
    //getNickName(qwe)
}

@Preview(showBackground = true)
@Composable
fun getMBTIPreview() {
    val qwe = remember {
        mutableStateOf("#MBTI")
    }
    //getMBTI(qwe)
}

@Preview(showBackground = true)
@Composable
fun getSelfIntroducePreview() {
    val qwe = remember {
        mutableStateOf("#학과 학번등 소개를 자유롭게 해주세요")
    }
    //getSelfIntroduce(introduce = qwe)
}

@Preview(showBackground = true)
@Composable
fun startButtonPreview() {
    startButton()
}

@Preview(showBackground = true)
@Composable
fun setTitlePreview() {
    setTitle()
}

