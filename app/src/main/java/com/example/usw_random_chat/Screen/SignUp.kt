package com.example.usw_random_chat.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.button


@Composable
fun SignUpScreen() {
    val rememberId = remember {
        mutableStateOf("")
    }
    val rememberPw = remember {
        mutableStateOf("")
    }
    val rememberPwCheck = remember {
        mutableStateOf("")
    }
    val rememberEmail = remember {
        mutableStateOf("")
    }
    var rememberPwVisible = remember {
        mutableStateOf(false)
    }

    var rememberPwCheckVisible = remember {
        mutableStateOf(false)
    }

    val rememberPwEqualOrNot = remember{
        mutableStateOf(false)
    }
    rememberPwEqualOrNot.value = rememberPw.value == rememberPwCheck.value

    val rememberTrigger = remember{
        mutableStateOf(false)
    }
    rememberTrigger.value = rememberPw.value == rememberPwCheck.value &&
            rememberId.value.isNotEmpty() &&
            rememberEmail.value.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {

        title()

        Column(
            Modifier.padding(15.dp)
        ) {
            IdWrite(id = rememberId)
            Spacer(Modifier.padding(15.dp))
            PwWrite(pw = rememberPw)
            PwCheck(
                pwCheck = rememberPwCheck,
                pwEqualOrNot = rememberPwEqualOrNot.value)
        }
        EmailTextFieldSignUp(email = rememberEmail)
        signUpBotton(trigger = rememberTrigger)
    }

}


@Composable
fun title() {
    Row(
        Modifier, horizontalArrangement = Arrangement.Center
    )
    {
        Spacer(Modifier.width(16.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
        }
        Text(
            text = buildAnnotatedString {
                append("회원가입")
            },
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(48.dp)
                .width(232.dp)
                .padding(start = 65.dp)
        )
    }
}

@Composable
fun IdWrite(id: MutableState<String>) {

    Row(
        Modifier, horizontalArrangement = Arrangement.Start

    ) {
        Text(
            text = "아이디",
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 15.dp)

        )
        if(id.value.length < 4 || id.value.length > 16) {
            Text(
                text = "*4자 이상 16자 이내로 작성해주세요",
                fontFamily = FontFamily.SansSerif,
                fontSize = 10.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF0000),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
            )
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = id.value,
            onValueChange = { idValue -> id.value = idValue
            },

            placeholder = { Text(text = "아이디 입력 (4~16자)", color = Color.Gray) },

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
                .padding(end = 8.dp)
        )
        Button(
            onClick = { /* Do something when the button is clicked */ },
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterVertically)
                .width(100.dp)
                .height(38.dp),

            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF2D64D8)
            ),

            ) {
            Text(text = "중복 확인")
        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PwWrite(pw: MutableState<String>,
            fontSize: TextUnit = 17.sp
) {

    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "비밀번호",
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 15.dp)

        )
        if(pw.value.length < 6 || pw.value.length > 20) {
            Text(
                text = "*6자 이상 20자 이내로 작성해 주세요",
                fontFamily = FontFamily.SansSerif,
                fontSize = 10.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF0000),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
            )
        }
    }
    val passwordVisible = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = pw.value,
            onValueChange = { pwValue -> pw.value = pwValue },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = { Text(text = "비밀번호 입력 (문자,숫자 포함 6~20자)", color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
                unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
                disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .height(50.dp)
                .width(300.dp)
        )//baseline_visibility_24
        IconButton(onClick = {   passwordVisible.value = !passwordVisible.value     }) {
            Icon(
                painter = painterResource(id = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = null // decorative element

            )
        }
    }
}

@Composable
fun PwCheck(
    pwCheck: MutableState<String>,
    pwEqualOrNot: Boolean,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 17.sp
) {


    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "비밀번호 확인",
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 15.dp)

        )
        if (!pwEqualOrNot) {
            Text(
                text = "*비밀번호가 일치하지 않습니다",
                fontFamily = FontFamily.SansSerif,
                fontSize = 10.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF0000),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
            )
        }
    }

    val passwordCheckVisible = remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ){
        TextField(
            value = pwCheck.value,
            onValueChange = { pwCheckValue -> pwCheck.value = pwCheckValue },
            visualTransformation = if (passwordCheckVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = { Text(text = "", color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
                unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
                disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .height(50.dp)
                .width(300.dp)
        )//baseline_visibility_24
        IconButton(onClick = { passwordCheckVisible.value = !passwordCheckVisible.value }) {
            Icon(
                painter = painterResource(id = if (passwordCheckVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = null // decorative element

            )
        }
    }
}

@Composable
fun EmailTextFieldSignUp(email: MutableState<String>) {

    Column(
        Modifier.padding(30.dp)

    ) {
        TextField(
            value = email.value,
            onValueChange = { emailValue -> email.value = emailValue },
            placeholder = { Text("포털 이메일 입력") },
            trailingIcon = { Text("@suwon.ac.kr    ", color = Color(0xFF000000)) },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            modifier = Modifier
                .width(326.dp)
                .heightIn(min = 56.dp)

        )
        Text(
            text = "* 이메일 형식이 올바르지 않습니다",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFF0000),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(top = 10.dp)
        )
    }
}

@Composable
fun signUpBotton(trigger: MutableState<Boolean>) {
    Column(
        Modifier.padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        button(
            "회원가입",
            enable = trigger.value,
            Color.White,
            Color.Black,
            Modifier
                .padding(top = 12.dp)
                .width(326.dp)
                .height(56.dp)
                .background(color = Color.White)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("회원가입 시  ")
                }
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("서비스 이용약관 ")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append(" 및 ")
                }
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("개인정보 처리방침")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("에 동의하신 것으로 간주됩니다")
                }
            },
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFDCDCDC),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(50.dp)
                .width(273.dp)
                .padding(3.dp, top = 10.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}