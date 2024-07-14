package com.example.usw_random_chat.presentation.view
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.ProfileViewModel
/*
@Composable
fun ProfileScreen(profileViewModel : ProfileViewModel = viewModel(),navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        setTitle{ navController.popBackStack() }
        getNickName(profileViewModel.nickname,"(필수)",{}) { profileViewModel.updateNickname(it) }
        getMBTI(profileViewModel.mbti,"(선택)",false) { profileViewModel.updateMBTI(it) }
        getSelfIntroduce(profileViewModel.selfintroduce,"(선택)",false) { profileViewModel.updateSelfIntroduce(it)}
        startButton{profileViewModel.postProfile()}
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
fun setTitle(onPress : () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(0.08f))
        IconButton(
            onClick = onPress
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
        }
        Spacer(modifier = Modifier.weight(0.25f))
        Text(
            text = "프로필 설정",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier
        )
        Spacer(modifier = Modifier.weight(0.5f))
    }
}
/*
@Composable
fun getNickName(nickname: State<String>, text : String, onNicknameChanged: (String) -> Unit) {
    Column(Modifier.padding(top = 40.dp, start = 32.dp)) {
        Row() {
            Text(text = "닉네임", fontSize = 16.sp, modifier = Modifier.padding(start = 5.dp))
            Text(
                text = text,
                color = Color.Red,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                modifier = Modifier.padding(start = 3.dp, top = 2.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 5.dp, end = 32.dp)
                .border(
                    width = 1.dp, color = Color(0xFFBFBFBF),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            TextField(
                value = nickname.value,
                onValueChange = onNicknameChanged,
                placeholder = { Text(text = "#NICKNAME", color = Color.Gray, fontSize = 13.sp) },
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
        Text(text = "* 닉네임은 8자 이내로 작성해 주세요", color = Color(0xFFFF6565), fontSize = 12.sp,fontFamily = FontFamily(Font(R.font.pretendard_regular)),modifier = Modifier.padding(start = 5.dp))
    }
}

@Composable
fun getMBTI(mbti: State<String>, text: String, filter : Boolean ,onMBTIChanged: (String) -> Unit) {
    Column(Modifier.padding(top = 10.dp)) {
        Row() {
            Text(text = "MBTI", fontSize = 16.sp,fontFamily = FontFamily(Font(R.font.pretendard_regular)),modifier = Modifier.padding(start = 5.dp))
            Text(
                text = text,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                color = Color.Gray,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 5.dp, start = 3.dp)
            )
        }
        Column {
            TextField(
                value = mbti.value,
                onValueChange = onMBTIChanged,
                placeholder = { Text(text = "#MBTI",fontFamily = FontFamily(Font(R.font.pretendard_regular)), fontSize = 13.sp) },
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
                    .border(
                        width = 1.dp,
                        color = Color(0xFFBFBFBF),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .height(50.dp)
                    .width(326.dp)
            )
            if (!filter){
                Text(text = "처음보는 MBTI에요! 올바른 MBTI를 입력해주세요! ", color = Color(0xFFFF6565), fontSize = 12.sp,fontFamily = FontFamily(Font(R.font.pretendard_regular)),modifier = Modifier.padding(start = 5.dp))
            }

        }

    }
}

@Composable
fun getSelfIntroduce(introduce: State<String>, text: String, filter: Boolean ,onSelfIntroduceChanged: (String) -> Unit) {
    Column(Modifier.padding(top = 10.dp)) {
        Row() {
            Text(text = "자기소개", fontSize = 16.sp,fontFamily = FontFamily(Font(R.font.pretendard_regular)),modifier = Modifier.padding(start = 5.dp))
            Text(
                text = text,
                color = Color.Gray,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                modifier = Modifier.padding(start = 3.dp, top = 5.dp)
            )
        }
        Column {
            TextField(
                value = introduce.value,
                onValueChange = onSelfIntroduceChanged,
                placeholder = { Text(text = "학과, 학번 등 소개를 자유롭게 입력하세요(40자 이내)",fontFamily = FontFamily(Font(R.font.pretendard_regular)), fontSize = 13.sp) },
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
            if (!filter){
                Text(text = "자기소개는 최대 40자에요!", color = Color(0xFFFF6565), fontSize = 12.sp,fontFamily = FontFamily(Font(R.font.pretendard_regular)),modifier = Modifier.padding(start = 5.dp))
            }
        }

    }
}
*/
@Composable
fun startButton(onPress: () -> Unit) {
    Box(modifier = Modifier.padding(top = 50.dp)) {
        Button(
            onClick = onPress,
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


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val nickname : State<String> = mutableStateOf("")
    val mbti : State<String> = mutableStateOf("")
    val selfintroduce : State<String> = mutableStateOf("")
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        setTitle{}
        getNickName(nickname,"(필수)",{}) {  }
        getMBTI(mbti,"(선택)",false) {  }
        getSelfIntroduce(selfintroduce,"(선택)",false) { }
        startButton{}
        Text(
            text = "프로필은 언제든 자유롭게\n수정할 수 있습니다",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 15.dp),
            fontFamily = FontFamily(Font(R.font.kcc_chassam))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun getNickNamePreview() {
    //getNickName(qwe)
}

@Preview(showBackground = true)
@Composable
fun getMBTIPreview() {
    //getMBTI(qwe)
}

@Preview(showBackground = true)
@Composable
fun getSelfIntroducePreview() {
    //getSelfIntroduce(introduce = qwe)
}

@Preview(showBackground = true)
@Composable
fun startButtonPreview() {
    //startButton(viewModel())
}

@Preview(showBackground = true)
@Composable
fun setTitlePreview() {
    setTitle(){}
}

*/