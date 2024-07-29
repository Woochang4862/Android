package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignUpViewModel

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = hiltViewModel(), navController: NavController)  {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        Spacer(Modifier.padding(20.dp))
        TittleWithBackArrow(
            "회원가입",
            Modifier
                .height(48.dp)
                .width(100.dp)
                .weight(0.6f)
                .offset(y = 10.dp),
            onBackClick = { navController.popBackStack() }
        )
        Spacer(Modifier.padding(top = 20.dp))
        writeID(
            signUpViewModel.rememberId,
            { newId -> signUpViewModel.updateRememberId(newId) },
            { signUpViewModel.checkSignUpId() }
        )
        Spacer(Modifier.padding(10.dp))
        writeNickName(signUpViewModel.nickName, signUpViewModel.checkSignupNickNameState.value,
            { newNickname -> signUpViewModel.updateRememberNickName(newNickname)}
        ) { signUpViewModel.checkSignUpNickName() }
        Spacer(Modifier.padding(15.dp))
        writePW(signUpViewModel.rememberPw) { signUpViewModel.updateRememberPw(it) }
        Spacer(Modifier.padding(5.dp))
        checkPW(signUpViewModel.rememberPwCheck, signUpViewModel.rememberPwEqualOrNot.value) {
            signUpViewModel.updateRememberPwCheck(it)
        }
        Spacer(Modifier.padding(20.dp))
        signUpNextButton(signUpViewModel.rememberTrigger.value, navController)
        if (signUpViewModel.checkSignupIdState.value) {
            //중복확인 성공했을때 이벤트
            OneButtonDialog(
                contentText = "아이디 사용이 \n가능합니다.",
                text = "확인",
                onPress = { signUpViewModel.changeCheckSignUpIdState() },
                image = R.drawable.baseline_error_24
            )
        }
        if (signUpViewModel.dialogCheckSignUpIdState.value) {
            OneButtonDialog(
                contentText = "아이디가 \n중복입니다.",
                text = "확인",
                onPress = { signUpViewModel.changeDialogCheckSignUpIdState() },
                image = R.drawable.baseline_error_24
            )
        }

        if (signUpViewModel.checkSignupNickNameState.value) {
            //중복확인 성공했을때 이벤트
            OneButtonDialog(
                contentText = "닉네임 사용이 \n가능합니다.",
                text = "확인",
                onPress = { signUpViewModel.changeCheckSignUpNickNameState() },
                image = R.drawable.baseline_error_24
            )
        }
        if (signUpViewModel.dialogCheckSignUpNickNameState.value) {
            OneButtonDialog(
                contentText = "닉네임익 \n중복입니다.",
                text = "확인",
                onPress = { signUpViewModel.changeDialogCheckSignUpNickNameState() },
                image = R.drawable.baseline_error_24
            )
        }
    }
}

@Composable
fun writeID(id: State<String>, onIdChanged: (String) -> Unit, onPress: () -> Unit) {
    val idLengthCheck = id.value.length < 4 || id.value.length > 16
    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.2f))

        TextFiledTitle(
            "아이디", "*4자 이상 16자 이내로 작성해주세요",
            Modifier
                .height(19.dp)
                .weight(0.24f), idLengthCheck,
            "                     ",
            Modifier
                .height(18.dp)
                .weight(1f)
                .padding(top = 3.dp)
        )

        Spacer(Modifier.weight(0.3f))
    }
    Spacer(Modifier.padding(5.dp))
    Row( modifier = Modifier.fillMaxWidth()){
        Spacer(Modifier.weight(0.1f))
        TextFieldSearchBtn(
            "아이디 입력 (4~16자)",
            textFieldIdValue = id.value,
            onValueChange = onIdChanged,
            idLengthCheck
        ) {
            onPress()
        }
        Spacer(Modifier.weight(0.1f))
    }

}

@Composable
fun writeNickName(
    nickname: State<String>,
    nicknameTrigger: Boolean,
    onNickNAmeChanged: (String) -> Unit,
    onPress: () -> Unit
) {
    val nickNameLength = nickname.value.length in 1..8
    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.2f))

        TextFiledTitle(
            "닉네임", "                ",
            Modifier
                .height(19.dp)
                .weight(0.24f), !nicknameTrigger or nickname.value.isEmpty(),
            "* 이미 사용중인 닉네임입니다",
            Modifier
                .height(18.dp)
                .weight(1f)
                .padding(top = 3.dp)
        )

        Spacer(Modifier.weight(0.3f))
    }
    Spacer(Modifier.padding(5.dp))
    Row( modifier = Modifier.fillMaxWidth()){
        Spacer(Modifier.weight(0.1f))
        TextFieldSearchBtn(
            "닉네임 입력",
            textFieldIdValue = nickname.value,
            onValueChange = onNickNAmeChanged,
            (!nickNameLength)
        ) {
            onPress()
        }
        Spacer(Modifier.weight(0.1f))
    }
    if(!nickNameLength) {
        Row(){
            Spacer(Modifier.weight(0.12f))
            RedWarning("* 닉네임은 8자 이내로 작성해주세요",
                Modifier
                    .height(20.dp)
                    .weight(0.9f)
                    .padding(top = 5.dp))
        }

}
}

@Composable
fun writePW(pw: State<String>, onRememberPw: (String) -> Unit) {
    val redBool = pw.value.length < 6 || pw.value.length > 20
    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.14f))

        TextFiledTitle(
            "비밀번호", "*6자 이상 20자 이내로 작성해 주세요",
            Modifier
                .height(19.dp)
                .weight(0.2f), redBool, "                      ",
            Modifier
                .height(18.dp)
                .weight(0.7f)
                .padding(top = 3.dp)
        )

        Spacer(Modifier.weight(0.15f))
    }
    Spacer(Modifier.padding(5.dp))

    VisibleText(pw, onRememberPw, "비밀번호 입력 (문자,숫자 포함 6~20자)")
}

@Composable
fun checkPW(
    pwCheck: State<String>,
    equalCheck: Boolean,
    onRememberPwCheck: (String) -> Unit,
) {
    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.145f))
        TextFiledTitle(
            "비밀번호 확인", "*비밀번호가 일치하지 않습니다",
            Modifier
                .height(19.dp)
                .weight(0.3f), !equalCheck && pwCheck.value.isNotEmpty(), "                ",
            Modifier
                .height(18.dp)
                .weight(0.7f)
                .padding(top = 3.dp)
        )
        Spacer(Modifier.weight(0.1f))
    }
    Spacer(Modifier.padding(5.dp))

    VisibleText(pwCheck, onRememberPwCheck, "")

}

@Composable
fun signUpNextButton(trigger: Boolean, navController: NavController) {
    Column(
        Modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier) {
            Spacer(Modifier.weight(0.1f))
            CustomButton(
                "다음",
                enable = trigger,
                Color.White,
                Color.Black,
                Modifier
                    .weight(0.8f)
                    .height(56.dp)
                    .background(color = Color.White)
            ) {
                navController.navigate(Screen.EmailAuthScreen.route)
            }
            Spacer(Modifier.weight(0.1f))
        }
        Row(Modifier) {
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