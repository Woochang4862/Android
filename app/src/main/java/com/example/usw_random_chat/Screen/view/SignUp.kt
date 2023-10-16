package com.example.usw_random_chat.Screen.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.font.Font
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.data.api.Register
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.GetScreenWidthInDp
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.idSearchBtn
import com.example.usw_random_chat.ui.portalEmail
import com.example.usw_random_chat.ui.tittleWithBackArrow
import retrofit2.Call
import retrofit2.Response


@Composable
fun SignUpScreen(navController: NavController) {
    val screenWidthInDp = (GetScreenWidthInDp())/2 -100
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

        Spacer(Modifier.padding(20.dp))

        tittleWithBackArrow("회원가입",modifier = Modifier
            .height(48.dp)
            .width(100.dp)
            .weight(0.6f)
            .offset(y=10.dp))
        Column(
            Modifier.padding(top =30.dp)
        ) {
            IdWrite(id = rememberId)
            Spacer(Modifier.padding(15.dp))
            PwWrite(pw = rememberPw)
            Spacer(Modifier.padding(5.dp))
            PwCheck(
                pwCheck = rememberPwCheck,
                pwEqualOrNot = rememberPwEqualOrNot.value)
            Spacer(Modifier.padding(20.dp))
        }
        EmailTextFieldSignUp(email = rememberEmail)
        Spacer(Modifier.padding(20.dp))
        signUpBotton(trigger = rememberTrigger, navController = navController)
    }

}

@Composable
fun IdWrite(id: MutableState<String>) {
    val screenWidthInDp = (GetScreenWidthInDp() - 326)/2
    Row(
        Modifier, horizontalArrangement = Arrangement.Start

    ) {
        Spacer(Modifier.weight(0.2f))
        Text(
            text = "아이디",
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier.height(19.dp).weight(0.24f)
        )
        if(id.value.length < 4 || id.value.length > 16) {
            Text(
                text = "*4자 이상 16자 이내로 작성해주세요",
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF0000),
                textAlign = TextAlign.Left,
                modifier = Modifier.height(18.dp).weight(1f).padding(top=3.dp)
            )
        }
        Spacer(Modifier.weight(0.3f))
    }
    Spacer(Modifier.padding(5.dp))

    idSearchBtn(textFieldIdValue = id.value, onValueChange = {idValue ->
        id.value = idValue}
    )
}

@Composable
fun PwWrite(pw: MutableState<String>,
            fontSize: TextUnit = 17.sp
) {
    val screenWidthInDp = (GetScreenWidthInDp() - 326)/2
    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.13f))
        Text(
            text = "비밀번호",
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier.height(19.dp).weight(0.2f)

        )
        if(pw.value.length < 6 || pw.value.length > 20) {
            Text(
                text = "*6자 이상 20자 이내로 작성해 주세요",
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF0000),
                textAlign = TextAlign.Left,
                modifier = Modifier.height(18.dp).weight(0.65f).padding(top=3.dp)
            )
        }
        Spacer(Modifier.weight(0.15f))
    }
    val passwordVisible = remember { mutableStateOf(false) }

    Spacer(Modifier.padding(5.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            //.width(360.dp)
            .padding(start = screenWidthInDp.dp,end = screenWidthInDp.dp)
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
                .weight(0.8f)
                //.padding(start= 5.dp)
        )//baseline_visibility_24
        IconButton(onClick = {   passwordVisible.value = !passwordVisible.value     }) {
            Icon(
                painter = painterResource(id = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = null // decorative element

            )
        }
        Spacer(Modifier.weight(0.02f))
    }
}

@Composable
fun PwCheck(
    pwCheck: MutableState<String>,
    pwEqualOrNot: Boolean,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 17.sp
) {

    val screenWidthInDp = (GetScreenWidthInDp() - 326)/2
    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {

        Text(
            text = "비밀번호 확인",
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier =  Modifier.height(19.dp).padding(start = (screenWidthInDp+5).dp)
            //여긴 가중치로 줄 경우 붉은 글씨가 뜰때 얘네가 움직여서 고정값으로 줌
        )
        if (!pwEqualOrNot) {
            Text(
                text = "*비밀번호가 일치하지 않습니다",
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFF0000),
                textAlign = TextAlign.Left,
                modifier = Modifier.height(18.dp).padding(start =15.dp,top=3.dp)
            )
        }
        Spacer(Modifier.weight(0.1f))
    }

    val passwordCheckVisible = remember { mutableStateOf(false) }

    Spacer(Modifier.padding(5.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp)
            //.width(360.dp)
            .padding(start = screenWidthInDp.dp, top = 3.dp,end = screenWidthInDp.dp)
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
                .weight(0.8f)
                //.padding(start= 5.dp)
        )//baseline_visibility_24
        IconButton(onClick = { passwordCheckVisible.value = !passwordCheckVisible.value }) {
            Icon(
                painter = painterResource(id = if (passwordCheckVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = null // decorative element

            )
        }
        Spacer(Modifier.weight(0.02f))
    }
}

@Composable
fun EmailTextFieldSignUp(email: MutableState<String>) {
    val screenWidthInDp = (GetScreenWidthInDp() - 326)/2
    Row(
        Modifier
    ) {
        Spacer(Modifier.weight(0.1f))
        portalEmail(textFieldValue = email.value, onValueChange = { emailValue ->
            email.value = emailValue }
        )
        Spacer(Modifier.weight(0.1f))
    }
}

@Composable
fun signUpBotton(trigger: MutableState<Boolean>, navController: NavController) {
    Column(
        Modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier) {
            Spacer(Modifier.weight(0.1f))
            button(
                "회원가입",
                enable = /*trigger.value,*/ true,
                Color.White,
                Color.Black,
                Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(color = Color.White)
            ) {
                Register.create()
                    .registerSignUp(
                        UserDTO("qzxhukuc890sdfeom","g45613423shgtewdf58","sasqp524lwpa23wesd")
                    )
                    .enqueue(object : retrofit2.Callback<UserDTO> {
                        override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                            if(response.isSuccessful) {
                                Log.d("회원가입성공", response.body().toString())
                                navController.navigate(Screen.SignUpDoneScreen.route){
                                    popUpTo(Screen.SignUpDoneScreen.route){
                                        inclusive = true
                                    }
                                }
                            }
                            else {
                                Log.w("회원가입실패",response.body().toString())
                            }
                        }
                        override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                            Log.e("연결 실패","${t.localizedMessage}")
                        }
                    })
            }
            Spacer(Modifier.weight(0.1f))
        }
        Row(Modifier){
            Spacer(Modifier.weight(0.2f))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0XFF989898))) {
                        append("회원가입 시  ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xff2D64D8))) {
                        append("서비스 이용약관 ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF989898))) {
                        append(" 및 ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xff2D64D8))) {
                        append("개인정보 처리방침")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF989898))) {
                        append("에 동의하신 것으로 간주됩니다")
                    }
                },
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFDCDCDC),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .padding(3.dp, top = 10.dp)
            )
            Spacer(Modifier.weight(0.2f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}