package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.GetScreenHeightInDp
import com.example.usw_random_chat.ui.button


@Composable // 제가 만들어 놓은 viewmodel 함수를 적용해서 완벽한 signin 화면을 만들어주세요, 어려우면 profile 화면 참고!!
fun SignInScreen(navController: NavController) {
    val editidState = remember {
        mutableStateOf("")
    }
    val editpasswordState = remember {
        mutableStateOf("")
    }
    val qwe = remember {
        mutableStateOf(false)
    }
    Box(){
        LoginImage()
        LoginTextField(id = editidState, password = editpasswordState)
    }
    LoginBtn(navController)
    OnLoginFindIdAndPassword()
    MadeAccountText()
    SignInBtn(navController,qwe)



}


@Composable
fun LoginImage() {//
    val screenHeightInDp = (GetScreenHeightInDp() - 576)
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ){
        Row(){
            Spacer(modifier = Modifier.weight(0.1f))
            Image(
                painter = painterResource(id = R.drawable.balloon),
                contentDescription = "image description",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(331.dp)
                    .weight(1f),
                alignment = Alignment.TopEnd
            )
        }
    }
}

@Composable
fun LoginTextField(  // textfield를 하나만 만들고 이름만 바꿔서 함수를 재사용 할 수 있게 변경해주세요 
    id: MutableState<String>,
    password: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 310.dp
            )
    ) {
        Row() {
            Spacer(modifier = Modifier.weight(0.1f))
            OutlinedTextField(
                value = id.value,
                onValueChange = { idValue -> id.value = idValue },
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(
                        text = "ID",
                        color = Color(0xFF989898),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_regular))
                        ),
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(color = Color.White)
            )
            Spacer(modifier = Modifier.weight(0.1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row() {
            Spacer(modifier = Modifier.weight(0.1f))
            OutlinedTextField(
                value = password.value,
                onValueChange = { passwordValue -> password.value = passwordValue },
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(
                        text = "PASSWORD",
                        color = Color(0xFF989898),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_regular))
                        ),
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                    )
                },
                modifier = Modifier
                    .height(48.dp)
                    .weight(1f)
                    .background(color = Color.White),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}

@Composable
fun LoginBtn(navController: NavController) { //onPress란 람다 함수를 추가시키세요
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 438.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        button(
            text = "로그인",
            enable = true,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .height(56.dp)
                .weight(1f)
        ){

        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun OnLoginFindIdAndPassword() { //textbutton 이름만 바꿔서 재사용 할 수 있게 수정해주세요 widget폴더에다 만들고 불러오세요
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 492.dp
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = {},
            modifier = Modifier
        ) {
            Text(
                text = "아이디 찾기",
                color = Color(0xFF232323),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                )
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.rectangle),
            contentDescription = "image description",
            modifier = Modifier
                .width(10.dp)
                .height(32.dp)
                .padding(top = 15.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        TextButton(
            onClick = {},
            modifier = Modifier

        ) {
            Text(
                text = "비밀번호 찾기",
                color = Color(0xFF232323),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                ),
            )
        }
    }
}


@Composable
fun MadeAccountText() { // 디바이더 함수도 widget폴더에 만들고 불러와서 사용해주세요
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 562.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.4f))
        Divider(
            color = Color(0xFFBFBFBF),
            modifier = Modifier
                .weight(1f)
                .padding(top = 9.dp)
        )
        Spacer(modifier = Modifier.weight(0.3f))

        Text(
            text = "계정이 없으신가요?",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 14.sp
            ),
            modifier = Modifier
                .weight(1.3f)
        )
        Spacer(modifier = Modifier.weight(0.3f))

        Divider(
            color = Color(0xFFBFBFBF),
            modifier = Modifier
                .weight(1f)
                .padding(top = 9.dp)
        )
        Spacer(modifier = Modifier.weight(0.4f))

    }
}

@Composable
fun SignInBtn(navController: NavController, asdasd : MutableState<Boolean>) { // asdasd변수 이름 적절하게 바꿔주세여
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 599.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        button(
            "회원가입",
            enable = true,
            Color.White,
            Color.Black,
            Modifier
                .height(56.dp)
                .weight(1f)
        ){
            asdasd.value = true
            navController.navigate(Screen.SignUpScreen.route)
            asdasd.value = false
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    val navController = rememberNavController() // NavController 초기화
    SignInScreen(navController)
}


@Preview(showBackground = true)
@Composable
fun OnLoginBtnPreview() {
    val navController = rememberNavController() // NavController 초기화
    LoginBtn(navController)
}


@Preview(showBackground = true)
@Composable
fun OnLoginFindIdAndPasswordPreview() {
    OnLoginFindIdAndPassword()
}

@Preview(showBackground = true)
@Composable
fun MadeAccountTextPreview() {
    MadeAccountText()
}


@Preview(showBackground = true)
@Composable
fun OnSignInBtnPreview() {
    val navController = rememberNavController()
    //OnSignInBtn(navController)
}


@Preview(showBackground = true)
@Composable
fun LoginTextFieldPreview() {
    val editidState = remember {
        mutableStateOf("")
    }
    val editpasswordState = remember {
        mutableStateOf("")
    }
    LoginTextField(id = editidState, password = editpasswordState)
}


@Preview(showBackground = true)
@Composable
fun OnLoginImagePreview() {
    LoginImage()
}