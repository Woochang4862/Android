package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignUpViewModel
import com.example.usw_random_chat.presentation.ViewModel.UserModifyViewModel
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.portalEmail

@Composable
fun PwSearchScreen(navController: NavController,userModifyViewModel: UserModifyViewModel = viewModel()) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        setPwSearchTitle()
        inputId(id = userModifyViewModel.rememberID){}
        Spacer(modifier = Modifier.height(21.dp))
        inputEmail(email = userModifyViewModel.rememberEmail){}
        explainText()
        sendNumberButton(){userModifyViewModel.postAuthCode()}
        inputCode(code = userModifyViewModel.rememberCode,{}){userModifyViewModel.checkAuthCode()}
    }
}

@Composable
fun setPwSearchTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 76.dp),
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(start = 22.dp)
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
        }
        Text(
            text = "비밀번호 찾기",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            modifier = Modifier
                .padding(top = 12.dp,start = 80.dp)
        )
    }
}

@Composable
fun inputId(id: State<String>, onChange: () -> Unit) {
    TextField(
        value = id.value,
        onValueChange = { onChange },
        placeholder = { Text("아이디 입력") },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        modifier = Modifier
            .width(326.dp)
            .heightIn(min = 56.dp)
            .padding(top = 42.dp)

    )

}

@Composable
fun inputEmail(email: State<String>, onChange: () -> Unit) {
    portalEmail(
        textFieldValue = email, onValueChange = {
            onChange
        })
}

@Composable
fun explainText() {
    Text(
        text = buildAnnotatedString {
            append("* 입력하신 메일 ")
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("인증코드")
            }
            append("를 전송합니다")
        },
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFF989898),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 60.dp)
    )
}

@Composable
fun sendNumberButton(onPress : () -> Unit) {
    button(
        text = "인증코드 전송",
        enable = true,
        content = Color.White,
        back = Color(0xFF2D64D8),
        modifier = Modifier
            .padding(top = 12.dp)
            .width(326.dp)
            .height(56.dp)
    ){
        onPress
    }
}

@Composable
fun inputCode(code: State<String>, onChange : () -> Unit, onPress: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 24.dp, start = 32.dp, end = 32.dp)
            .width(326.dp)
            .height(56.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = code.value,
            onValueChange = { onChange },
            placeholder = { Text(text = "인증코드 4자리 입력", color = Color.Gray) },
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
        )
        Button(
            onClick = { onPress },
            modifier = Modifier
                .padding(end = 6.dp)
                .align(Alignment.CenterVertically)
                .width(70.dp)
                .height(38.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF2D64D8)
            ),
        ) {
            Text(
                "확인",
                fontSize = 14.sp,
                color = Color.White,
                lineHeight = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PwSearchScreenPreview() {
    val id = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val code = remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        setPwSearchTitle()
        inputId(id = id){}
        Spacer(modifier = Modifier.height(21.dp))
        inputEmail(email = email){}
        explainText()
        sendNumberButton(){}
        inputCode(code = code,{}){}
    }
}