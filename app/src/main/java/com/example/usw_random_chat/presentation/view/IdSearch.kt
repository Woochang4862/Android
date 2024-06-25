package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.UserModifyViewModel

@Composable
fun IdSearch(userModifyViewModel: UserModifyViewModel = viewModel(), navController: NavController){
    IdSearchEmail(email = userModifyViewModel.email){ userModifyViewModel.updateEmail(it) }
    IdText()
    IdSearchEmailBtn(){userModifyViewModel.postAuthEmail()}
    GoLogin(navController)
    IdSearchExitBtn{navController.popBackStack()}

    if (userModifyViewModel.checkIdSearchAuthEmail.value){
        OneButtonDialog(
            contentText = "확인 메일이\n 전송됐습니다.",
            text = "확인",
            onPress = { userModifyViewModel.changeCheckIdSearchAuthEmail() },
            image = R.drawable.baseline_error_24
        )
    }
    if(userModifyViewModel.dialogCheckIdSearchAuthEmail.value){
        OneButtonDialog(
            contentText = "포털 메일 정보가\n 올바르지 않습니다.",
            text = "확인",
            onPress = { userModifyViewModel.changeDialogCheckIdSearchAuthEmail() },
            image = R.drawable.baseline_error_24
        )
    }
}


@Composable
fun IdSearchExitBtn(onPress: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 80.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        TittleWithBackArrow(
            "아이디 찾기",
            Modifier
                .height(48.dp)
                .width(100.dp)
                .weight(0.6f)
                .offset(y = 10.dp),
            onBackClick = { onPress() }
        )
        Spacer(modifier = Modifier.weight(1.1f))
    }
}


@Composable
fun IdSearchEmail(
    email: State<String>,
    onValueEmail: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 143.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        PortalEmail(text = email, onValueChange = onValueEmail)
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun IdText(){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 256.dp
            ),
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        CustomText(
            text1 = "*입력하신 메일로 ",
            text2 = "가입된 아이디 ",
            text3 = "정보를 전송합니다",
            modifier = Modifier
                .height(18.dp)
                .weight(1f)
        )
        Spacer(modifier = Modifier.weight(0.3f))
    }
}

@Composable
fun IdSearchEmailBtn(onPress: () -> Unit){

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 286.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        CustomButton(
            text = "확인메일 전송",
            enable = true,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
        ) {
            onPress()
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}


@Composable
fun GoLogin(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 354.dp
            )
    ){
        Spacer(modifier = Modifier.weight(0.1f))
        CustomButton(
            "로그인 하러가기",
            enable = true,
            Color.White,
            Color.Black,
            Modifier
                .weight(1f)
                .height(56.dp)
                .background(color = Color.White)
        ){
            navController.navigate(Screen.SignInScreen.route)
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}



@Preview (showBackground = true)
@Composable
fun IdSearchPreview(navController: NavController = rememberNavController()){
    val email: State<String>  = remember{
        mutableStateOf("")
    }

    IdSearchEmail(email){  }
    IdText()
    IdSearchEmailBtn(){}
    GoLogin(navController)
    IdSearchExitBtn{}
}


/*@Preview (showBackground = true)
@Composable
fun IdSearchExitBtnPreview(navController: NavController) {
    IdSearchExitBtn(navController)
}
@Preview (showBackground = true)
@Composable
fun IdSearchEmailPreview() {

    val editTextState = remember {
        mutableStateOf("")
    }
    IdSearchEmail(editTextState)
}*/