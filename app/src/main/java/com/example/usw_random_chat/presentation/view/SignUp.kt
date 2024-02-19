package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignUpViewModel
import com.example.usw_random_chat.ui.OneButtonDialog
import com.example.usw_random_chat.ui.TextfiledTitle
import com.example.usw_random_chat.ui.VisibleText
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.textFieldSearchBtn
import com.example.usw_random_chat.ui.tittleWithBackArrow

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel(), navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        Spacer(Modifier.padding(20.dp))
        tittleWithBackArrow(
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
            { newNickname -> signUpViewModel.updateRememberNickName(newNickname) }
        ) { signUpViewModel.checkSignUpNickName() }
        Spacer(Modifier.padding(15.dp))
        writePW(signUpViewModel.rememberPw) { signUpViewModel.updateRememberPw(it) }
        Spacer(Modifier.padding(5.dp))
        checkPW(signUpViewModel.rememberPwCheck, signUpViewModel.rememberPwEqualOrNot.value) {
            signUpViewModel.updateRememberPwCheck(it)
        }
        Spacer(Modifier.padding(20.dp))
        signUpButton(signUpViewModel.rememberTrigger.value) {
            navController.navigate(Screen.EmailAuthScreen.route)
        }
        if (signUpViewModel.checkSignupIdState.value) {
            //중복확인 성공했을때 이벤트
            signUpViewModel.changeCheckSignUpIdState()
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
            signUpViewModel.changeCheckSignUpNickNameState()
        }
        if (signUpViewModel.dialogCheckSignUpNickNameState.value) {
            OneButtonDialog(
                contentText = "닉네임익 \n중복입니다.",
                text = "확인",
                onPress = { signUpViewModel.changeDialogCheckSignUpNickNameState() },
                image = R.drawable.baseline_error_24
            )
        }// 이건 닉네임 중복 확인에 쓰는 건데 닉네임 부분이 없어요

        if (signUpViewModel.signupState.value) {
            navController.navigate(Screen.SignInScreen.route) {
                navController.popBackStack()
            }
            signUpViewModel.changeSignupState()
        }
        if (signUpViewModel.dialogSignupState.value) {
            OneButtonDialog(
                contentText = "아이디 혹은 비밀번호가\n올바르지 않습니다.",
                text = "확인",
                onPress = { signUpViewModel.changeDialogSignupState() },
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

        TextfiledTitle(
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
    textFieldSearchBtn(
        "아이디 입력 (4~16자)",
        textFieldIdValue = id.value,
        onValueChange = onIdChanged,
        idLengthCheck
    ) {
        onPress()
    }
}

@Composable
fun writeNickName(
    nickname: State<String>,
    nicknameTrigger: Boolean,
    onNickNAmeChanged: (String) -> Unit, onPress: () -> Unit
) {
    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.2f))

        TextfiledTitle(
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
    textFieldSearchBtn(
        "닉네임 입력",
        textFieldIdValue = nickname.value,
        onValueChange = onNickNAmeChanged,
        nickname.value.isEmpty()
    ) {
        onPress()
    }
}

@Composable
fun writePW(pw: State<String>, onRememberPw: (String) -> Unit) {
    val redBool = pw.value.length < 6 || pw.value.length > 20
    Row(
        Modifier, horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.14f))

        TextfiledTitle(
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

    VisibleText(pw, onRememberPw, "비밀번호 입력 (문자,숫자 포함 6~20자)", 1f, 0.0005f)
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
        TextfiledTitle(
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

    VisibleText(pwCheck, onRememberPwCheck, "", 0.8f, 0.0005f)

}

/*@Composable
fun EmailTextFieldSignUp(email: State<String>, onRememberEmail: (String) -> Unit) {
    Row(
        Modifier
    ) {
        Spacer(Modifier.weight(0.1f))
        portalEmail(
            textFieldValue = email, onValueChange = onRememberEmail
        )
        Spacer(Modifier.weight(0.1f))
    }
}*/

@Composable
fun signUpButton(trigger: Boolean, onPress: () -> Unit) {
    Column(
        Modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier) {
            Spacer(Modifier.weight(0.1f))
            button(
                "다음",
                enable = trigger,
                Color.White,
                Color.Black,
                Modifier
                    .weight(0.8f)
                    .height(56.dp)
                    .background(color = Color.White)
            ) {
                onPress()
            }
            Spacer(Modifier.weight(0.1f))
        }
        Row(Modifier) {
            // 아래 부분 코드를 더 줄일 수 있는 방법이 있는지 고민해주세요
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