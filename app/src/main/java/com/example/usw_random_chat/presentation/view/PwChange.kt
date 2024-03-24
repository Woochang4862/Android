package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.UserModifyViewModel
import com.example.usw_random_chat.ui.TextFiledTitle
import com.example.usw_random_chat.ui.VisibleText
import com.example.usw_random_chat.ui.CustomButton
import com.example.usw_random_chat.ui.OneButtonDialog
import com.example.usw_random_chat.ui.TittleWithBackArrow


@Composable
fun PwChangeScreen(
    userModifyViewModel: UserModifyViewModel = viewModel(),
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {

        Spacer(Modifier.padding(20.dp))

        TittleWithBackArrow(
            "비밀번호 변경",
            modifier = Modifier
                .height(48.dp)
                .width(100.dp)
                .offset(y = 10.dp)
                .weight(0.6f),
            onBackClick = { navController.popBackStack() }
        )

        Spacer(Modifier.padding(15.dp))

        TextFieldOfPwChange( userModifyViewModel.rememberPW) {
            userModifyViewModel.updateRememberPW(it)
        }
        Spacer(Modifier.padding(10.dp))
        TextFieldOfPwCheck( userModifyViewModel.rememberPWCheck,
            userModifyViewModel.rememberPwEqualOrNot.value
        ) {
            userModifyViewModel.updateRememberPWCheck(it)
        }

        Spacer(Modifier.padding(20.dp))
        PwChangeButton(userModifyViewModel.rememberTrigger.value, navController = navController) {
            userModifyViewModel.changePW()
        }
    }
    if (userModifyViewModel.checkIdSearchAuthEmail.value){
        OneButtonDialog(
            contentText = "비밀번호가 변경되었습니다.",
            text = "확인",
            onPress = { userModifyViewModel.changeCheckIdSearchAuthEmail() },
            image = R.drawable.baseline_error_24
        )
    }

}

@Composable
fun TextFieldOfPwChange(
    pw: State<String>,
    onRememberPw: (String) -> Unit
) {
    val redBool = pw.value.length < 6 || pw.value.length > 20
    Row(
        Modifier
    ) {
        Spacer(Modifier.weight(0.17f))

        TextFiledTitle("비밀번호","* 6자 이상 20자 이내로 작성해 주세요",
            Modifier
                .height(19.dp)
                .weight(0.24f),
            redBool,"                       ",
            Modifier
                .height(18.dp)
                .weight(0.8f)
                .padding(top = 3.dp)
        )

        Spacer(Modifier.weight(0.3f))

    }

    Spacer(Modifier.padding(5.dp))

    VisibleText(pw,onRememberPw,"새 비밀번호 입력 (문자,숫자 포함 6~20자)")
}


@Composable
fun TextFieldOfPwCheck(
    pwcheck: State<String>,
    equal: Boolean,
    onRememberPwCheck: (String) -> Unit
) {
    Row(
        Modifier
    ) {
        Spacer(Modifier.weight(0.19f))
        TextFiledTitle("비밀번호 확인","* 비밀번호가 일치하지 않습니다",
            Modifier
                .height(19.dp)
                .weight(0.4f),!equal,"                 ",
            Modifier
                .height(18.dp)
                .weight(0.8f)
                .padding(top = 3.dp))

        Spacer(Modifier.weight(0.3f))
    }

    Spacer(Modifier.padding(5.dp))

    VisibleText(pwcheck,onRememberPwCheck,"")

}

@Composable
fun PwChangeButton(trigger: Boolean, navController: NavController, onPress: () -> Unit) {
    Row(
        Modifier
    ) {
        Spacer(Modifier.weight(0.1f))
        CustomButton(
            text = "변경완료",
            enable = trigger,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                //.width(326.dp)
                .weight(1f)
                .height(56.dp)
        ){
            onPress()
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