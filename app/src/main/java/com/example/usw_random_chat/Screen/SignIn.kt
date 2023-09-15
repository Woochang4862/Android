package com.example.usw_random_chat.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.GetScreenHeightInDp
import com.example.usw_random_chat.ui.GetScreenWidthInDp
import com.example.usw_random_chat.ui.button
import kotlinx.coroutines.NonDisposableHandle.parent


@Composable
fun SignInScreen(navController: NavController) {
    val editidState = remember {
        mutableStateOf("")
    }
    val editpasswordState = remember {
        mutableStateOf("")
    }
    Box(){
        OnLoginImage()
        LoginTextField(id = editidState, password = editpasswordState)
    }
    OnLoginBtn(navController)
    OnLoginFindIdAndPassword()
    MadeAccountText()
    OnSignInBtn(navController)
}


@Composable
fun OnLoginImage() {
    val screenHeightInDp = (GetScreenHeightInDp() - 576)
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ){
        Image(
            painter = painterResource(id = R.drawable.balloon),
            contentDescription = "image description",
            modifier = Modifier
                .fillMaxWidth()
                .height(331.dp)
                .padding(
                    start = 32.dp,
                    top = 40.dp
                ),
            alignment = Alignment.TopEnd
        )
    }
}

@Composable
fun LoginTextField(
    id: MutableState<String>,
    password: MutableState<String>
) {
    val screenHeightInDp = (GetScreenHeightInDp() - 481)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (idbutton,passwordbutton) = createRefs()

        OutlinedTextField(
            value = id.value,
            onValueChange = {idValue -> id.value = idValue},
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
                .constrainAs(idbutton) {
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    bottom.linkTo(parent.bottom, margin = 338.dp)
                    width = Dimension.fillToConstraints
                }
                .height(48.dp)
                .background(color = Color.White)
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = {passwordValue -> password.value = passwordValue},
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
                .constrainAs(passwordbutton) {
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    bottom.linkTo(parent.bottom, margin = 282.dp)
                    width = Dimension.fillToConstraints
                }
                .height(48.dp)
                .background(color = Color.White),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
    }
}

@Composable
fun OnLoginBtn(navController: NavController) {
    val screenHeightInDp = (GetScreenHeightInDp() - 357)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (loginbutton) = createRefs()
        button(
            text = "로그인",
            enable = true,
            content = Color.White,
            back = Color(0xFF2D64D8),
            modifier = Modifier
                .constrainAs(loginbutton) {
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    bottom.linkTo(parent.bottom, margin = 202.dp)
                    width = Dimension.fillToConstraints
                }
                .height(56.dp)
        ){
            navController.navigate(Screen.MainPageScreen.route){
                popUpTo(Screen.MainPageScreen.route){
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun OnLoginFindIdAndPassword() {
    val screenHeightInDp = (GetScreenHeightInDp() - 294)

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (idbutton, image, passwordbutton) = createRefs()

        TextButton(
            onClick = {},
            modifier = Modifier
                .constrainAs(idbutton) {
                    end.linkTo(image.start, margin = 9.dp)
                    bottom.linkTo(parent.bottom , margin = 155.dp)
                }
        ) {
            Text(
                text = "아이디 찾기",
                color = Color(0xFF232323),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.rectangle),
            contentDescription = "image description",
            modifier = Modifier
                .constrainAs(image) {
                    bottom.linkTo(parent.bottom, margin = 163.dp)
                    centerHorizontallyTo(parent)
                }
                .width(8.dp)
                .height(30.dp)
        )
        TextButton(
            onClick = {},
            modifier = Modifier
                .constrainAs(passwordbutton) {
                    start.linkTo(image.end, margin = 9.dp)
                    bottom.linkTo(parent.bottom , margin = 155.dp)
                }
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
fun MadeAccountText() {
    val screenHeightInDp = (GetScreenHeightInDp() - 238)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (divider1,divider2,text) = createRefs()
        Divider(
            color = Color(0xFFBFBFBF),
            modifier = Modifier
                .constrainAs(divider1) {
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(text.start, margin = 21.dp)
                    bottom.linkTo(parent.bottom, margin = 132.dp)
                    width = Dimension.fillToConstraints
                }
                .height(1.dp)
        )
        Text(
            text = "계정이 없으신가요?",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 14.sp
            ),
            modifier = Modifier
                .constrainAs(text) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom, margin = 124.dp)
                }
        )
        Divider(
            color = Color(0xFFBFBFBF),
            modifier = Modifier
                .constrainAs(divider2) {
                    end.linkTo(parent.end, margin = 32.dp)
                    start.linkTo(text.end, margin = 21.dp)
                    bottom.linkTo(parent.bottom, margin = 132.dp)
                    width = Dimension.fillToConstraints
                }
        )
    }
}

@Composable
fun OnSignInBtn(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (madeidbutton) = createRefs()
        button(
            "회원가입",
            enable = true,
            Color.White,
            Color.Black,
            Modifier
                .constrainAs(madeidbutton) {
                    bottom.linkTo(parent.bottom, margin = 50.dp)
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    width = Dimension.fillToConstraints
                }
                .height(56.dp),
        ){
            navController.navigate(Screen.SignUpScreen.route)
        }
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
    OnLoginBtn(navController)
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
    OnSignInBtn(navController)
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
    OnLoginImage()
}