package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.UserModifyViewModel
import com.example.usw_random_chat.ui.GetScreenWidthInDp
import com.example.usw_random_chat.ui.RedWarning
import com.example.usw_random_chat.ui.button
import com.example.usw_random_chat.ui.tittleWithBackArrow


@Composable
fun PwChangeScreen(userModifyViewModel: UserModifyViewModel = viewModel(), navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {

        Spacer(Modifier.padding(20.dp))

        tittleWithBackArrow(
            "비밀번호 변경",
            modifier = Modifier
                .height(48.dp)
                .width(100.dp)
                .offset(y = 10.dp)
                .weight(0.6f),
            onBackClick = { navController.popBackStack() }
        )

        Spacer(Modifier.padding(15.dp))

        TextFieldOfPwChange("새 비밀번호 입력 (문자,숫자 포함 6~20자)",
            "비밀번호",userModifyViewModel.rememberPW ){
            userModifyViewModel.updateRememberPW(it)}
        Spacer(Modifier.padding(10.dp))
        TextFieldOfPwCheck("", "비밀번호 확인"
            ,userModifyViewModel.rememberPWCheck, userModifyViewModel.rememberPwEqualOrNot.value){
            userModifyViewModel.updateRememberPWCheck(it)
        }

        Spacer(Modifier.padding(20.dp))
        PwChangeBotton(userModifyViewModel.rememberTrigger.value,navController = navController){
            userModifyViewModel.postPwChange()
        }
    }
}

@Composable
fun TextFieldOfPwChange(
    inWord: String,
    name: String,
    pw : State<String>,
    onRememberPw: (String) -> Unit
) {
    val passwordVisible = remember { mutableStateOf(false) }
    Row(
        Modifier, //horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.15f))
        Text(
            text = name,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier.height(19.dp).weight(0.24f)

        )
        if(pw.value.length < 6 || pw.value.length>20) {
            RedWarning(
                "* 6자 이상 20자 이내로 작성해 주세요",
                modifier = Modifier.height(18.dp).weight(0.8f).padding(top = 3.dp)
            )
        }else {
            RedWarning(
                "                       ",
                Modifier
                    .height(18.dp)
                    .weight(0.8f)
                    .padding(top = 3.dp)
            )
        }
            Spacer(Modifier.weight(0.3f))

    }

    Spacer(Modifier.padding(5.dp))

    Row(Modifier){
        Spacer(Modifier.weight(0.1f))
        TextField(
            value = pw.value,
            onValueChange = onRememberPw,
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = { Text(text = inWord, color = Color.Gray, fontSize = 14.sp)},
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
                .height((50.dp))
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
                .border(
                    width = 1.dp, color = Color(0xFFBFBFBF),
                    shape = RoundedCornerShape(8.dp)
                )
        )
        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
            Icon(
                painter = painterResource(id = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = null // decorative element
            )
        }
        Spacer(Modifier.weight(0.1f))
    }
}


@Composable
fun TextFieldOfPwCheck(
    inWord: String,
    name: String,
    pwcheck : State<String>,
    equal : Boolean,
    onRememberPwCheck: (String) -> Unit
) {
    val passwordVisible = remember { mutableStateOf(false) }
    Row(
        Modifier, //horizontalArrangement = Arrangement.Start
    ) {
        Spacer(Modifier.weight(0.165f))
        Text(
            text = name,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Left,
            modifier = Modifier.height(19.dp).weight(0.4f)
        )
        if(!equal) {
            RedWarning(
                "* 비밀번호가 일치하지 않습니다",
                modifier = Modifier.height(18.dp).weight(0.8f).padding(top = 3.dp)
            )
        }
        else {
            RedWarning(
                "                 ",
                modifier = Modifier.height(18.dp).weight(0.8f).padding(top = 3.dp)
            )
        }
            Spacer(Modifier.weight(0.3f))
    }

    Spacer(Modifier.padding(5.dp))

    Row(Modifier){
        Spacer(Modifier.weight(0.1f))
        TextField(
        value = pwcheck.value,
        onValueChange = onRememberPwCheck,
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = { Text(text = inWord, color = Color.Gray, fontSize = 14.sp)},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
            unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
            disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
        ),
        // shape 속성 주석 처리
        // shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            //.width(368.dp)
            .weight(1f)
            .height((50.dp))
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
        )
        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
            Icon(
                painter = painterResource(id = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                contentDescription = null // decorative element
            )
        }
        Spacer(Modifier.weight(0.1f))
    }
}
@Composable
fun PwChangeBotton(trigger: Boolean, navController: NavController, onPress: () -> Unit) {
    Row(
        Modifier
    ) {
        Spacer(Modifier.weight(0.1f))
        button(
            text = "변경완료",
            enable = trigger,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                //.width(326.dp)
                .weight(1f)
                .height(56.dp)
        ){
        onPress
        navController.navigate(Screen.SignInScreen.route)
        }
        Spacer(Modifier.weight(0.1f))
    }

}


@Preview(showBackground = true)
@Composable
fun PwChangeScreenPreview() {
    PwChangeScreen(navController = rememberNavController())
}