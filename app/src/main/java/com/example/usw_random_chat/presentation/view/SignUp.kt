package com.example.usw_random_chat.presentation.view

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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.SignUpViewModel
import com.example.usw_random_chat.ui.GetScreenWidthInDp
import com.example.usw_random_chat.ui.OneButtonDialog
import com.example.usw_random_chat.ui.RedWarning
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.idSearchBtn
import com.example.usw_random_chat.ui.loginTextField
import com.example.usw_random_chat.ui.text
import com.example.usw_random_chat.ui.tittleWithBackArrow

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel(), navController: NavController) {
    val screenWidthInDp = (GetScreenWidthInDp() - 326) / 2

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
        writeID(signUpViewModel.rememberId) { signUpViewModel.updateRememberId(it) }
        Spacer(Modifier.padding(15.dp))
        writePW(signUpViewModel.rememberPw) { signUpViewModel.updateRememberPw(it) }
        Spacer(Modifier.padding(5.dp))
        checkPW(signUpViewModel.rememberPwCheck, signUpViewModel.rememberPwEqualOrNot.value) {
            signUpViewModel.updateRememberPwCheck(it)
        }
        Spacer(Modifier.padding(20.dp))
        signUpButton(signUpViewModel.rememberTrigger.value){
            signUpViewModel.postSignUp()
        }
        if (signUpViewModel.checkSignupIdState.value){
            //중복확인 성공했을때 이벤트
            signUpViewModel.changeCheckSignUpIdState()
        }
        if(signUpViewModel.dialogCheckSignUpIdState.value){
            OneButtonDialog(
                contentText = "아이디가 \n중복입니다.",
                text = "확인",
                onPress = { signUpViewModel.changeDialogCheckSignUpIdState() },
                image = R.drawable.baseline_error_24
            )
        }

        if (signUpViewModel.signupState.value){
            navController.navigate(Screen.SignInScreen.route){
                navController.popBackStack()
            }
            signUpViewModel.changeSignupState()
        }
        if(signUpViewModel.dialogSignupState.value){
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
fun writeID(id: State<String>, onIdChanged: (String) -> Unit) {
    val idLengthCheck = id.value.length < 4 || id.value.length > 16
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
            modifier = Modifier
                .height(19.dp)
                .weight(0.24f)
        )
        if (idLengthCheck) {
            RedWarning(
                "*4자 이상 16자 이내로 작성해주세요",
                Modifier
                    .height(18.dp)
                    .weight(1f)
                    .padding(top = 3.dp)
            )
        } else {
            RedWarning(
                "                     ",
                Modifier
                    .height(18.dp)
                    .weight(1f)
                    .padding(top = 3.dp)
            )
        }
        Spacer(Modifier.weight(0.3f))
    }
    Spacer(Modifier.padding(5.dp))
    idSearchBtn(textFieldIdValue = id.value, onValueChange = onIdChanged, idLengthCheck) {

    }
}

@Composable
fun writePW(pw: State<String>, onRememberPw: (String) -> Unit) {
    val screenWidthInDp = (GetScreenWidthInDp() - 326) / 2
    val passwordVisible = remember { mutableStateOf(false) }
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
            modifier = Modifier
                .height(19.dp)
                .weight(0.2f)
        )
        if (pw.value.length < 6 || pw.value.length > 20) {
            RedWarning(
                "*6자 이상 20자 이내로 작성해 주세요",
                Modifier
                    .height(18.dp)
                    .weight(0.65f)
                    .padding(top = 3.dp)
            )
        } else {
            RedWarning(
                "                      ",
                Modifier
                    .height(18.dp)
                    .weight(0.65f)
                    .padding(top = 3.dp)
            )
        }
        Spacer(Modifier.weight(0.15f))
    }
    Spacer(Modifier.padding(5.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(start = screenWidthInDp.dp, end = screenWidthInDp.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = pw.value,
            onValueChange = onRememberPw,
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
        )
        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
            Icon(
                painter = painterResource(id = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = null // decorative element
            )
        }
        Spacer(Modifier.weight(0.02f))
    }
}

@Composable
fun checkPW(
    pwCheck: State<String>,
    equalCheck: Boolean,
    onRememberPwCheck: (String) -> Unit,
) {
    val passwordCheckVisible = remember { mutableStateOf(false) }
    val screenWidthInDp = (GetScreenWidthInDp() - 326) / 2
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
            modifier = Modifier
                .height(19.dp)
                .padding(start = (screenWidthInDp + 5).dp)
            //여긴 가중치로 줄 경우 붉은 글씨가 뜰때 얘네가 움직여서 고정값으로 줌
        )
        if (!equalCheck) {
            RedWarning(
                "*비밀번호가 일치하지 않습니다",
                Modifier
                    .height(18.dp)
                    .padding(start = 15.dp, top = 3.dp)
            )
        }
        Spacer(Modifier.weight(0.1f))
    }
    Spacer(Modifier.padding(5.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp)
            //.width(360.dp)
            .padding(start = screenWidthInDp.dp, top = 3.dp, end = screenWidthInDp.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = pwCheck.value,
            onValueChange = onRememberPwCheck,
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
        )
        IconButton(onClick = { passwordCheckVisible.value = !passwordCheckVisible.value }) {
            Icon(
                painter = painterResource(id = if (passwordCheckVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = null // decorative element
            )
        }
        Spacer(Modifier.weight(0.02f))
    }
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
                "회원가입 완료",
                enable = trigger,
                Color.White,
                Color.Black,
                Modifier
                    .weight(1f)
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