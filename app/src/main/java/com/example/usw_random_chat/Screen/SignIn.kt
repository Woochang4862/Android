package com.example.usw_random_chat.Screen

import android.util.Log
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
import com.example.usw_random_chat.Backend.Register
import com.example.usw_random_chat.DTO.UserDTO
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.GetScreenHeightInDp
import com.example.usw_random_chat.ui.GetScreenWidthInDp
import com.example.usw_random_chat.ui.button
import kotlinx.coroutines.NonDisposableHandle.parent
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


@Composable
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
        OnLoginImage()
        LoginTextField(id = editidState, password = editpasswordState)
    }
    OnLoginBtn(navController)
    OnLoginFindIdAndPassword()
    MadeAccountText()
    OnSignInBtn(navController,qwe)
    Register.create()
        .registerSignIn(UserDTO(
            memberEmail = editidState.value,
            memberPassword = editpasswordState.value,
            memberName = "이경수"))
        .enqueue(object : retrofit2.Callback<UserDTO> {
            override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                val result = response.code();
                if(result in 200..299) {
                    Log.d("회원가입성공", response.body().toString())
                    navController.navigate(Screen.MainPageScreen.route){
                        popUpTo(Screen.MainPageScreen.route){
                            inclusive = true
                        }
                    }
                }
                else {
                    Log.w("회원가입실패", response.body().toString())

                }
            }

            override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                Log.e("연결 실패","${t.localizedMessage}")
            }
    })

}


@Composable
fun OnLoginImage() {
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
fun LoginTextField(
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
fun OnLoginBtn(navController: NavController) {
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
            navController.navigate(Screen.MainPageScreen.route){
                popUpTo(Screen.MainPageScreen.route){
                    inclusive = true
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun OnLoginFindIdAndPassword() {
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
fun MadeAccountText() {
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
fun OnSignInBtn(navController: NavController, asdasd : MutableState<Boolean>) {
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
    OnLoginImage()
}